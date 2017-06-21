package strawman
package collection
package immutable

import scala.language.higherKinds
import _root_.cats._

package object cats {
  implicit val catsStdInstancesForEmptyList: TraverseFilter[EmptyList] with MonadCombine[EmptyList] with Monad[EmptyList] with CoflatMap[EmptyList] =
    new TraverseFilter[EmptyList] with MonadCombine[EmptyList] with Monad[EmptyList] with CoflatMap[EmptyList] {
      def empty[A]: EmptyList[A] = EmptyList
      def combineK[A](x: EmptyList[A], y: EmptyList[A]): EmptyList[A] = EmptyList
      def pure[A](x: A): EmptyList[A] = EmptyList
      def flatMap[A, B](fa: EmptyList[A])(f: A => EmptyList[B]): EmptyList[B] = EmptyList
      def tailRecM[A, B](a: A)(f: A => EmptyList[Either[A, B]]): EmptyList[B] = EmptyList
      def coflatMap[A, B](fa: EmptyList[A])(f: EmptyList[A] => B): EmptyList[B] = EmptyList
      def foldLeft[A, B](fa: EmptyList[A], b: B)(f: (B, A) => B): B = b
      def foldRight[A, B](fa: EmptyList[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = lb
      def traverseFilter[G[_], A, B](fa: EmptyList[A])(f: A => G[Option[B]])(implicit G: Applicative[G]): G[EmptyList[B]] = G.pure(EmptyList)
    }
}
