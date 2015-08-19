val openers_to_closers_map = Map(
  '(' -> ')',
  '{' -> '}',
  '[' -> ']'
)

val openers = openers_to_closers_map.keys

val closers = openers_to_closers_map.values


openers.exists(x => x == '(')
closers.exists(x => x == '(')

for (c <- " a[T](t[T]) = { f(t); } ".toCharArray) {
  println(c)
}

