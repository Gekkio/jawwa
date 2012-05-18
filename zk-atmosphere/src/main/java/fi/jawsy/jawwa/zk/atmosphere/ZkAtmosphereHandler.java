package fi.jawsy.jawwa.zk.atmosphere;

import static com.google.common.base.Predicates.not;
import static fi.jawsy.jawwa.lang.Option.option;
import static fi.jawsy.jawwa.lang.Option.requireType;
import static fi.jawsy.jawwa.lang.StringPredicates.isEmpty;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.val;

import org.atmosphere.cpr.AtmosphereHandler;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.http.WebManager;
import org.zkoss.zk.ui.sys.DesktopCtrl;
import org.zkoss.zk.ui.sys.WebAppCtrl;

import fi.jawsy.jawwa.lang.Either;

/**
 * Atmosphere handler that integrates Atmosphere with ZK server push.
 */
public class ZkAtmosphereHandler implements AtmosphereHandler {

    @Override
    public void destroy() {
    }

    private Either<String, Desktop> getDesktop(Session session, String dtid) {
        for (val webAppCtrl : requireType(session.getWebApp(), WebAppCtrl.class)) {
            return option(webAppCtrl.getDesktopCache(session).getDesktopIfAny(dtid)).toRight("Could not find desktop");
        }
        return Either.left("Webapp does not implement WebAppCtrl");
    }

    private Either<String, String> getDesktopId(HttpServletRequest request) {
        return option(request.getParameter("dtid")).filter(not(isEmpty())).toRight("Could not find desktop id");
    }

    private Either<String, AtmosphereServerPush> getServerPush(AtmosphereResource resource) {
        val request = resource.getRequest();

        val sessionEither = getSession(resource, request);
        for (val error : sessionEither.getLeft()) {
            return Either.left(error);
        }
        val session = sessionEither.getRightValue();
        {
            val dtidEither = getDesktopId(request);
            for (val error : dtidEither.getLeft()) {
                return Either.left(error);
            }

            val dtid = dtidEither.getRightValue();
            {
                val desktopEither = getDesktop(session, dtid);
                for (val error : desktopEither.getLeft()) {
                    return Either.left(error);
                }

                val desktop = desktopEither.getRightValue();
                return getServerPush(desktop);
            }
        }
    }

    private Either<String, AtmosphereServerPush> getServerPush(Desktop desktop) {
        for (val desktopCtrl : requireType(desktop, DesktopCtrl.class)) {
            if (desktopCtrl.getServerPush() == null)
                return Either.left("Server push is not enabled");
            for (val serverPush : requireType(desktopCtrl.getServerPush(), AtmosphereServerPush.class)) {
                return Either.right(serverPush);
            }
            return Either.left("Server push implementation is not AtmosphereServerPush");
        }
        return Either.left("Desktop does not implement DesktopCtrl");
    }

    private Either<String, Session> getSession(AtmosphereResource resource, HttpServletRequest request) {
        return option(WebManager.getSession(resource.getAtmosphereConfig().getServletContext(), request, false)).toRight("Could not find session");
    }

    @Override
    public void onRequest(AtmosphereResource resource) throws IOException {
        val response = resource.getResponse();

        response.setContentType("text/plain");

        val serverPushEither = getServerPush(resource);
        for (val error : serverPushEither.getLeft()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write(error);
            return;
        }

        val serverPush = serverPushEither.getRightValue();
        serverPush.updateResource(resource);
    }

    @Override
    public void onStateChange(AtmosphereResourceEvent event) throws IOException {
        val resource = event.getResource();

        if (event.isCancelled() || event.isResumedOnTimeout()) {
            for (val serverPush : getServerPush(resource).getRight()) {
                serverPush.clearResource(resource);
            }
        }
    }

}
