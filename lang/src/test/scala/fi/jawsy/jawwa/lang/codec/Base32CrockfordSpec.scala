package fi.jawsy.jawwa.lang.codec

import org.junit.runner.RunWith
import org.specs2.{ Specification, ScalaCheck }
import org.specs2.runner.JUnitRunner
import com.google.common.base.Charsets

@RunWith(classOf[JUnitRunner])
class Base32CrockfordSpec extends Specification with ScalaCheck {

  import Base32Crockford.{ decode, encode }

  def is =
    "Base32Crockford should" ^
      "encode an empty array" ! {
        encode(Array()) must_== ""
      } ^
      "decode an empty string" ! {
        decode("").isEmpty must beTrue
      } ^
      "encode and decode byte arrays correctly" ! check { (value: List[Byte]) =>
        val encoded = encode(value.toArray)
        val decoded = decode(encoded)
        decoded.toList must_== value
      }

}