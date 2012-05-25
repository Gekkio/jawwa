package fi.jawsy.jawwa.zk.metrics

import com.google.common.collect.ImmutableList
import com.yammer.metrics.core.MetricsRegistry
import org.junit.runner.RunWith
import org.specs2.mock.Mockito
import org.specs2.runner.JUnitRunner
import org.specs2.specification.After
import org.specs2.Specification
import org.zkoss.zk.ui.event.Event
import org.zkoss.zk.ui.{ Session, Execution, Desktop }

@RunWith(classOf[JUnitRunner])
class ZkMetricsImplSpec extends Specification {
  def is =
    "ZkMetricsImpl should" ^
      "track active desktops" ! metrics().activeDesktops ^
      "track active events" ! metrics().activeEvents ^
      "track active sessions" ! metrics().activeSessions ^
      "track active updates" ! metrics().activeUpdates ^
      "measure client-side request completion duration" ! metrics().clientRequestCompletedDuration ^
      "measure client-side request receiving duration" ! metrics().clientRequestReceivedDuration ^
      "count desktop creation" ! metrics().desktopsCreated ^
      "count desktop destruction" ! metrics().desktopsDestroyed ^
      "measure event processing duration" ! metrics().eventProcessedDuration ^
      "count posted events" ! metrics().eventsPosted ^
      "count processed events" ! metrics().eventsProcessed ^
      "count sent events" ! metrics().eventsSent ^
      "measure server-side request completion duration" ! metrics().serverRequestCompletedDuration ^
      "count created sessions" ! metrics().sessionsCreated ^
      "count destroyed sessions" ! metrics().sessionsDestroyed ^
      "count processed updates" ! metrics().updatesProcessed ^
      "count started updates" ! metrics().updatesStarted

  case class metrics() extends After with Mockito {
    val registry = new MetricsRegistry
    val m = new ZkMetricsImpl(registry)
    def after = registry.shutdown()

    val desktop = mock[Desktop]
    val session = mock[Session]
    val event = mock[Event]
    val request = "request"
    val execution = mock[Execution]

    def activeDesktops = this{
      {
        m.desktopCreated(desktop)
        m.getActiveDesktops.count must_== 1
      } and {
        m.desktopDestroyed(desktop)
        m.getActiveDesktops.count must_== 0
      }
    }

    def activeEvents = this{
      {
        m.beforeProcessEvent(event)
        m.getActiveEvents.count must_== 1
      } and {
        m.afterProcessEvent(event)
        m.getActiveEvents.count must_== 0
      }
    }

    def activeSessions = this{
      {
        m.sessionCreated(session)
        m.getActiveSessions.count must_== 1
      } and {
        m.sessionDestroyed(session)
        m.getActiveSessions.count must_== 0
      }
    }

    def activeUpdates = this{
      {
        m.beforeUpdate(desktop, ImmutableList.of())
        m.getActiveUpdates.count must_== 1
      } and {
        m.afterUpdate(desktop)
        m.getActiveUpdates.count must_== 0
      }
    }

    def clientRequestCompletedDuration = this{
      m.requestStartAtClient(request, execution, 5)
      m.requestStartAtServer(request, execution, 10)
      m.requestCompleteAtServer(request, execution, 15)
      m.requestReceiveAtClient(request, execution, 20)
      m.requestCompleteAtClient(request, execution, 25)

      (m.getClientRequestCompletedDuration.count must_== 1) and
        (m.getClientRequestCompletedDuration.max must beCloseTo(25 - 5, 0.1))
    }

    def clientRequestReceivedDuration = this{
      m.requestStartAtClient(request, execution, 5)
      m.requestStartAtServer(request, execution, 10)
      m.requestCompleteAtServer(request, execution, 15)
      m.requestReceiveAtClient(request, execution, 20)

      (m.getClientRequestReceivedDuration.count must_== 1) and
        (m.getClientRequestReceivedDuration.max must beCloseTo(20 - 5, 0.1))
    }

    def desktopsCreated = this{
      m.desktopCreated(desktop)

      m.getDesktopsCreated.count must_== 1
    }

    def desktopsDestroyed = this{
      m.desktopDestroyed(desktop)

      m.getDesktopsDestroyed.count must_== 1
    }

    def eventProcessedDuration = this{
      m.beforeProcessEvent(event)
      Thread.sleep(20)
      m.afterProcessEvent(event)

      (m.getEventProcessedDuration.count must_== 1) and
        (m.getEventProcessedDuration.max must be_>(15.0))
    }

    def eventsPosted = this{
      m.beforePostEvent(event)
      m.getEventsPosted.count must_== 1
    }

    def eventsProcessed = this{
      m.beforeProcessEvent(event)
      m.afterProcessEvent(event)
      m.getEventsProcessed.count must_== 1
    }

    def eventsSent = this{
      m.beforeSendEvent(event)
      m.getEventsSent.count must_== 1
    }

    def serverRequestCompletedDuration = this{
      m.requestStartAtClient(request, execution, 5)
      m.requestStartAtServer(request, execution, 10)
      m.requestCompleteAtServer(request, execution, 15)

      (m.getServerRequestCompletedDuration.count must_== 1) and
        (m.getServerRequestCompletedDuration.max must beCloseTo(15 - 10, 0.1))
    }

    def sessionsCreated = this{
      m.sessionCreated(session)

      m.getSessionsCreated.count must_== 1
    }

    def sessionsDestroyed = this{
      m.sessionDestroyed(session)

      m.getSessionsDestroyed.count must_== 1
    }

    def updatesProcessed = this{
      m.afterUpdate(desktop)
      m.getUpdatesProcessed.count must_== 1
    }

    def updatesStarted = this{
      m.beforeUpdate(desktop, ImmutableList.of())
      m.getUpdatesStarted.count must_== 1
    }
  }

}

