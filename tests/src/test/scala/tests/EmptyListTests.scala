package tests

import cats.implicits._
import strawman.collection.immutable.cats._
import strawman.collection.immutable._

import org.scalatest.FunSuite

class RxTests extends FunSuite {
  test("EmptyList is empty") {
    assert(EL.isEmpty)
  }
}
