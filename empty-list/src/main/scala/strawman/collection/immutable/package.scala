package strawman
package collection

package object immutable {
  type EL[+T] = EmptyList[T]
  val  EL     = EmptyList
}
