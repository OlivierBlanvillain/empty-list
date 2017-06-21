package strawman
package collection
package immutable

trait EmptyList[+T] extends Product with Serializable
final case object EmptyList extends EmptyList[Nothing]
