package com.aoc

import com.aoc.CustomAssertions.Companion.assertRopeLocation
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class RopeMoverTest {
  @Nested
  class `Head movements`() {
    @Test
    fun right() {
      var r = Rope()
      r = RopeMover.moveRight(r)
      assertRopeLocation(listOf(Pair(1, 0), Pair(0, 0)), r)
    }

    @Test
    fun down() {
      var r = Rope()
      r = RopeMover.moveDown(r)
      assertRopeLocation(listOf(Pair(0, -1), Pair(0, 0)), r)
    }

    @Test
    fun left() {
      var r = Rope()
      r = RopeMover.moveLeft(r)
      assertRopeLocation(listOf(Pair(-1, 0), Pair(0, 0)), r)
    }

    @Test
    fun up() {
      var r = Rope()
      r = RopeMover.moveUp(r)
      assertRopeLocation(listOf(Pair(0, 1), Pair(0, 0)), r)
    }
  }

  @Nested
  class `Segment movements` {
    @Nested
    class `Segment doesn't move` {
      @Nested
      class `Previous moves on top of current` {
        @Test
        fun right() {
          var r = Rope()
          r.moveSegments(listOf(Pair(2, 3), Pair(3, 3)))
          r = RopeMover.moveRight(r)
          assertRopeLocation(listOf(Pair(3, 3), Pair(3, 3)), r)
        }

        @Test
        fun down() {
          var r = Rope()
          r.moveSegments(listOf(Pair(3, 4), Pair(3, 3)))
          r = RopeMover.moveDown(r)
          assertRopeLocation(listOf(Pair(3, 3), Pair(3, 3)), r)
        }

        @Test
        fun left() {
          var r = Rope()
          r.moveSegments(listOf(Pair(4, 3), Pair(3, 3)))
          r = RopeMover.moveLeft(r)
          assertRopeLocation(listOf(Pair(3, 3), Pair(3, 3)), r)
        }

        @Test
        fun up() {
          var r = Rope()
          r.moveSegments(listOf(Pair(3, 2), Pair(3, 3)))
          r = RopeMover.moveUp(r)
          assertRopeLocation(listOf(Pair(3, 3), Pair(3, 3)), r)
        }
      }

      @Nested
      class `Previous moves next to current` {
        @Test
        fun right() {
          var r = Rope()
          r.moveSegments(listOf(Pair(2, 2), Pair(3, 3)))
          r = RopeMover.moveRight(r)
          assertRopeLocation(listOf(Pair(3, 2), Pair(3, 3)), r)
        }

        @Test
        fun down() {
          var r = Rope()
          r.moveSegments(listOf(Pair(2, 4), Pair(3, 3)))
          r = RopeMover.moveDown(r)
          assertRopeLocation(listOf(Pair(2, 3), Pair(3, 3)), r)
        }

        @Test
        fun left() {
          var r = Rope()
          r.moveSegments(listOf(Pair(4, 4), Pair(3, 3)))
          r = RopeMover.moveLeft(r)
          assertRopeLocation(listOf(Pair(3, 4), Pair(3, 3)), r)
        }

        @Test
        fun up() {
          var r = Rope()
          r.moveSegments(listOf(Pair(4, 2), Pair(3, 3)))
          r = RopeMover.moveUp(r)
          assertRopeLocation(listOf(Pair(4, 3), Pair(3, 3)), r)
        }
      }

      @Nested
      class `Previous moves diagonal to current` {
        fun right() {
          var r = Rope()
          r.moveSegments(listOf(Pair(0, 1), Pair(0, 0)))
          r = RopeMover.moveRight(r)
          assertRopeLocation(listOf(Pair(1, 1), Pair(0, 0)), r)
        }

        @Test
        fun down() {
          var r = Rope()
          r.moveSegments(listOf(Pair(1, 0), Pair(0, 0)))
          r = RopeMover.moveDown(r)
          assertRopeLocation(listOf(Pair(1, -1), Pair(0, 0)), r)
        }

        @Test
        fun left() {
          var r = Rope()
          r.moveSegments(listOf(Pair(0, -1), Pair(0, 0)))
          r = RopeMover.moveLeft(r)
          assertRopeLocation(listOf(Pair(-1, -1), Pair(0, 0)), r)
        }

        @Test
        fun up() {
          var r = Rope()
          r.moveSegments(listOf(Pair(-1, 0), Pair(0, 0)))
          r = RopeMover.moveUp(r)
          assertRopeLocation(listOf(Pair(-1, 1), Pair(0, 0)), r)
        }
      }
    }

    @Nested
    class `Segment moves one space in cardinal direction` {
      @Test
      fun right() {
        var r = Rope()
        r.moveSegments(listOf(Pair(8, 6), Pair(7, 6)))
        r = RopeMover.moveRight(r)
        assertRopeLocation(listOf(Pair(9, 6), Pair(8, 6)), r)
      }

      @Test
      fun down() {
        var r = Rope()
        r.moveSegments(listOf(Pair(7, 5), Pair(7, 6)))
        r = RopeMover.moveDown(r)
        assertRopeLocation(listOf(Pair(7, 4), Pair(7, 5)), r)
      }

      @Test
      fun left() {
        var r = Rope()
        r.moveSegments(listOf(Pair(6, 6), Pair(7, 6)))
        r = RopeMover.moveLeft(r)
        assertRopeLocation(listOf(Pair(5, 6), Pair(6, 6)), r)
      }

      @Test
      fun up() {
        var r = Rope()
        r.moveSegments(listOf(Pair(7, 7), Pair(7, 6)))
        r = RopeMover.moveUp(r)
        assertRopeLocation(listOf(Pair(7, 8), Pair(7, 7)), r)
      }
    }

    @Nested
    class `Segment moves diagonally` {
      @Nested
      class `Previous is diagonal, moves further away` {
        @Test
        fun right() {
          var r = Rope()
          r.moveSegments(listOf(Pair(4, 2), Pair(3, 3)))
          r = RopeMover.moveRight(r)
          assertRopeLocation(listOf(Pair(5, 2), Pair(4, 2)), r)
        }

        @Test
        fun down() {
          var r = Rope()
          r.moveSegments(listOf(Pair(2, 2), Pair(3, 3)))
          r = RopeMover.moveDown(r)
          assertRopeLocation(listOf(Pair(2, 1), Pair(2, 2)), r)
        }

        @Test
        fun left() {
          var r = Rope()
          r.moveSegments(listOf(Pair(2, 4), Pair(3, 3)))
          r = RopeMover.moveLeft(r)
          assertRopeLocation(listOf(Pair(1, 4), Pair(2, 4)), r)
        }

        @Test
        fun up() {
          var r = Rope()
          r.moveSegments(listOf(Pair(4, 4), Pair(3, 3)))
          r = RopeMover.moveUp(r)
          assertRopeLocation(listOf(Pair(4, 5), Pair(4, 4)), r)
        }
      }

      @Nested
      class `Current was next to previous, previous moved diagonal` {
        @Test
        fun `Up and Right`() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(2, 1), Pair(1, 0), Pair(0, 0)))
          r = RopeMover.moveUp(r)
          assertRopeLocation(listOf(Pair(2, 2), Pair(2, 1), Pair(1, 1)), r)
        }

        @Test
        fun `Up and Left`() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(-2, 1), Pair(-1, 0), Pair(0, 0)))
          r = RopeMover.moveUp(r)
          assertRopeLocation(listOf(Pair(-2, 2), Pair(-2, 1), Pair(-1, 1)), r)
        }

        @Test
        fun `Down and Right`() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(2, -1), Pair(1, 0), Pair(0, 0)))
          r = RopeMover.moveDown(r)
          assertRopeLocation(listOf(Pair(2, -2), Pair(2, -1), Pair(1, -1)), r)
        }

        @Test
        fun `Down and Left`() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(-2, -1), Pair(-1, 0), Pair(0, 0)))
          r = RopeMover.moveDown(r)
          assertRopeLocation(listOf(Pair(-2, -2), Pair(-2, -1), Pair(-1, -1)), r)
        }
      }

      @Nested
      class `Current was diagonal to previous, previous moved diagonal` {
        @Test
        fun up() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(2, 2), Pair(1, 1), Pair(0, 0)))
          r = RopeMover.moveUp(r)
          assertRopeLocation(listOf(Pair(2, 3), Pair(2, 2), Pair(1, 1)), r)
        }

        @Test
        fun right() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(2, 2), Pair(1, 1), Pair(0, 0)))
          r = RopeMover.moveRight(r)
          assertRopeLocation(listOf(Pair(3, 2), Pair(2, 2), Pair(1, 1)), r)
        }

        @Test
        fun down() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(-2, -2), Pair(-1, -1), Pair(0, 0)))
          r = RopeMover.moveDown(r)
          assertRopeLocation(listOf(Pair(-2, -3), Pair(-2, -2), Pair(-1, -1)), r)
        }

        @Test
        fun left() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(-2, -2), Pair(-1, -1), Pair(0, 0)))
          r = RopeMover.moveLeft(r)
          assertRopeLocation(listOf(Pair(-3, -2), Pair(-2, -2), Pair(-1, -1)), r)
        }
      }

      @Nested
      class `Current was diagonal to previous, previous is nowin line with currentl` {
        @Test
        fun left() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(0, 2), Pair(1, 1), Pair(0, 0)))
          r = RopeMover.moveLeft(r)
          assertRopeLocation(listOf(Pair(-1, 2), Pair(0, 2), Pair(0, 1)), r)
        }

        @Test
        fun down() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(-2, 0), Pair(-1, 1), Pair(0, 0)))
          r = RopeMover.moveDown(r)
          assertRopeLocation(listOf(Pair(-2, -1), Pair(-2, 0), Pair(-1, 0)), r)
        }

        @Test
        fun right() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(0, -2), Pair(-1, -1), Pair(0, 0)))
          r = RopeMover.moveRight(r)
          assertRopeLocation(listOf(Pair(1, -2), Pair(0, -2), Pair(0, -1)), r)
        }

        @Test
        fun up() {
          var r = Rope(3)
          r.moveSegments(listOf(Pair(2, 0), Pair(1, -1), Pair(0, 0)))
          r = RopeMover.moveUp(r)
          assertRopeLocation(listOf(Pair(2, 1), Pair(2, 0), Pair(1, 0)), r)
        }
      }
    }
  }
}
