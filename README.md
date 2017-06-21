# empty-list [![Travis](https://api.travis-ci.org/OlivierBlanvillain/empty-list.png?branch=master)](https://travis-ci.org/OlivierBlanvillain/empty-list) [![Gitter](https://badges.gitter.im/Join%20Chat.svg)](https://github.com/OlivierBlanvillain/empty-list/issues/new?title=I%20want%20to%20chat,%20please%20open%20a%20Gitter) [![Maven](https://img.shields.io/maven-central/v/in.nvilla/empty-list_sjs0.6_2.12.svg?label=maven)](https://repo1.maven.org/maven2/in/nvilla/empty-list_sjs0.6_2.12/)

The empty list collection.

The goal of this project is to open the discussion on the potential addition of an empty list collection to the standard library. This is a counter proposal to the idea of a non empty list that has been discussed repetitively for the collection strawman.

Try is out from sbt:

```scala
"in.nvilla" %% "empty-list" % "1.0" // use %%% for Scala.js
```

The empty list collection is [defined as follows](empty-list/src/main/scala/strawman/collection/immutable/EmptyList.scala):

```scala
trait EmptyList[+T] extends Product with Serializable
final case object EmptyList extends EmptyList[Nothing]
```

The library also provides [useful shorthands](empty-list/src/main/scala/strawman/collection/immutable/package.scala) for the `EmptyList` type and value:

```scala
type EL[+T] = EmptyList[T]
val  EL     = EmptyList
```

A separate artifact with cats' [type class instances](empty-list-cats/src/main/scala/strawman/collection/immutable/cats/package.scala) for `EmptyList` is published as `empty-list-cats`.

The empty-list project and contributors support the [Empty Code of Conduct](code-of-conduct.md) and want all its associated channels (e.g. GitHub, Gitter) to be a safe and friendly environment for everyone.

## FAQ

#### How is `EmptyList` different from `Nil`?

1. `scala.collection.immutable.Nil` defines unsafe operation such as `head` and `tail`. `EmptyList` doesn't. `EmptyList` is simply a *better type* to indicate emptiness!

    ```scala
    scala> Nil.head
    java.util.NoSuchElementException: head of empty list
      at scala.collection.immutable.Nil$.head(List.scala:428)
      ... 29 elided

    scala> Nil.tail
    java.lang.UnsupportedOperationException: tail of empty list
      at scala.collection.immutable.Nil$.tail(List.scala:430)
      ... 29 elided
    ```

2. `scala.collection.immutable.Nil` loses it's emptiness type information as soon as it's used with any operation such as `map` of `flatMap`. Thanks to having it's own functor instance, `EmptyList` properly propagate type information!

    ```scala
    scala> Nil.map(identity)
    res1: List[Nothing] = List()
    ```

    ```scala
    scala> EL.map(identity)
    res2: EmptyList.type
    ```

3. `scala.collection.immutable.Nil` has unhygienic equality, it can be compared to anything without scalac emitting any warning. `Nil` is also (surprisingly) equal to other empty collections that inherit `scala.collection.GenSeq[_]`:

    ```scala
    scala> Nil == Vector.empty
    res3: Boolean = true
    ```

    `EmptyList` is a proper algebraic data type, things always work as expected:

    ```scala
    scala> EL == Vector.empty
    <console>:1: warning: comparing values of types EL and Vector[Nothing] using `==` is always false
           EL == Vector.empty
              ^
    res4: Boolean = false
    ```
