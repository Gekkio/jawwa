package fi.jawsy.jawwa.zk.gritter

import org.junit.runner.RunWith
import org.specs2.mutable.Specification
import org.specs2.runner.JUnitRunner

@RunWith(classOf[JUnitRunner])
class GritterSpec extends Specification {

  "A Gritter notification" should {

    "require only title and text attributes" in {
      val n = Gritter.notification.withTitle("Title").withText("Text").build
      n.toJSONString must be_==("{\"title\":\"Title\",\"text\":\"Text\"}")
    }
    /* Impossible to test without proper ZK execution scope
    "support image attribute" in {}
    */
    "support sticky attribute" in {
      val n = Gritter.notification.withTitle("Title").withText("Text").withSticky(true).build
      n.toJSONString must be_==("{\"title\":\"Title\",\"text\":\"Text\",\"sticky\":true}")
    }
    "support time attribute" in {
      val n = Gritter.notification.withTitle("Title").withText("Text").withTime(1).build
      n.toJSONString must be_==("{\"title\":\"Title\",\"text\":\"Text\",\"time\":1}")
    }
    "support class_name attribute" in {
      val n = Gritter.notification.withTitle("Title").withText("Text").withSclass("clz").build
      n.toJSONString must be_==("{\"title\":\"Title\",\"text\":\"Text\",\"class_name\":\"clz\"}")
    }

  }

}
