package ru.timakden.adventofcode

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import ru.timakden.adventofcode.year2015.day11.checkPassword
import ru.timakden.adventofcode.year2015.day17.getContainers
import ru.timakden.adventofcode.year2015.day25.generateNextCode
import ru.timakden.adventofcode.year2016.day08.createScreen
import ru.timakden.adventofcode.year2015.day01.solvePartOne as y15d01p01
import ru.timakden.adventofcode.year2015.day01.solvePartTwo as y15d01p02
import ru.timakden.adventofcode.year2015.day02.solvePartOne as y15d02p01
import ru.timakden.adventofcode.year2015.day02.solvePartTwo as y15d02p02
import ru.timakden.adventofcode.year2015.day03.solvePartOne as y15d03p01
import ru.timakden.adventofcode.year2015.day03.solvePartTwo as y15d03p02
import ru.timakden.adventofcode.year2015.day04.solve as y15d04
import ru.timakden.adventofcode.year2015.day05.solvePartOne as y15d05p01
import ru.timakden.adventofcode.year2015.day05.solvePartTwo as y15d05p02
import ru.timakden.adventofcode.year2015.day06.solvePartOne as y15d06p01
import ru.timakden.adventofcode.year2015.day06.solvePartTwo as y15d06p02
import ru.timakden.adventofcode.year2015.day08.solvePartOne as y15d08p01
import ru.timakden.adventofcode.year2015.day08.solvePartTwo as y15d08p02
import ru.timakden.adventofcode.year2015.day09.solve as y15d09
import ru.timakden.adventofcode.year2015.day10.solve as y15d10
import ru.timakden.adventofcode.year2015.day12.solve as y15d12
import ru.timakden.adventofcode.year2015.day13.solve as y15d13
import ru.timakden.adventofcode.year2015.day17.solve as y15d17
import ru.timakden.adventofcode.year2015.day19.solvePartOne as y15d19p01
import ru.timakden.adventofcode.year2015.day19.solvePartTwo as y15d19p02
import ru.timakden.adventofcode.year2015.day20.solve as y15d20
import ru.timakden.adventofcode.year2015.day23.solve as y15d23
import ru.timakden.adventofcode.year2015.day24.solve as y15d24
import ru.timakden.adventofcode.year2015.day25.solve as y15d25
import ru.timakden.adventofcode.year2016.day01.solvePartOne as y16d01p01
import ru.timakden.adventofcode.year2016.day01.solvePartTwo as y16d01p02
import ru.timakden.adventofcode.year2016.day02.solve as y16d02
import ru.timakden.adventofcode.year2016.day03.solvePartOne as y16d03p01
import ru.timakden.adventofcode.year2016.day03.solvePartTwo as y16d03p02
import ru.timakden.adventofcode.year2016.day04.solvePartOne as y16d04p01
import ru.timakden.adventofcode.year2016.day05.solvePartOne as y16d05p01
import ru.timakden.adventofcode.year2016.day05.solvePartTwo as y16d05p02
import ru.timakden.adventofcode.year2016.day06.solve as y16d06
import ru.timakden.adventofcode.year2016.day07.solve as y16d07
import ru.timakden.adventofcode.year2016.day08.solvePartOne as y16d08p01
import ru.timakden.adventofcode.year2016.day09.solvePartOne as y16d09p01
import ru.timakden.adventofcode.year2016.day09.solvePartTwo as y16d09p02
import ru.timakden.adventofcode.year2016.day10.solve as y16d10

@DisplayName("Advent of Code Tests")
class AdventOfCodeTest {
    @Test
    @DisplayName("year 2015, day 01")
    fun year2015day01() {
        assertEquals(3, y15d01p01("(()(()("))
        assertEquals(-3, y15d01p01(")())())"))
        assertEquals(1, y15d01p02(")"))
        assertEquals(5, y15d01p02("()())"))
    }

    @Test
    @DisplayName("year 2015, day 02")
    fun year2015day02() {
        assertEquals(58, y15d02p01(listOf("2x3x4")))
        assertEquals(43, y15d02p01(listOf("1x1x10")))
        assertEquals(34, y15d02p02(listOf("2x3x4")))
        assertEquals(14, y15d02p02(listOf("1x1x10")))

    }

    @Test
    @DisplayName("year 2015, day 03")
    fun year2015day03() {
        assertEquals(2, y15d03p01(">"))
        assertEquals(4, y15d03p01("^>v<"))
        assertEquals(2, y15d03p01("^v^v^v^v^v"))
        assertEquals(3, y15d03p02("^v"))
        assertEquals(3, y15d03p02("^>v<"))
        assertEquals(11, y15d03p02("^v^v^v^v^v"))
    }

    @Test
    @DisplayName("year 2015, day 04")
    fun year2015day04() {
        assertEquals(609043, y15d04("abcdef", false))
        assertEquals(1048970, y15d04("pqrstuv", false))
    }

    @Test
    @DisplayName("year 2015, day 05")
    fun year2015day05() {
        var input = listOf("ugknbfddgicrmopn", "aaa", "jchzalrnumimnmhp", "haegwjzuvuyypxyu", "dvszwmarrgswjxmb")
        assertEquals(2, y15d05p01(input))

        input = listOf("qjhvhtzxzqqjkmpb", "xxyxx", "uurcxstgmygtbstg", "ieodomkazucvgmuy")
        assertEquals(2, y15d05p02(input))
    }

    @Test
    @DisplayName("year 2015, day 06")
    fun year2015day06() {
        assertEquals(1000000, y15d06p01(listOf("turn on 0,0 through 999,999")))
        assertEquals(1, y15d06p02(listOf("turn on 0,0 through 0,0")))
        assertEquals(2000000, y15d06p02(listOf("toggle 0,0 through 999,999")))
    }

    @Test
    @DisplayName("year 2015, day 08")
    fun year2015day08() {
        val input = listOf("""""""", """"abc"""", """"aaa\"aaa"""", """"\x27"""")
        assertEquals(12, y15d08p01(input))
        assertEquals(19, y15d08p02(input))
    }

    @Test
    @DisplayName("year 2015, day 09")
    fun year2015day09() {
        val input = listOf("London to Dublin = 464", "London to Belfast = 518", "Dublin to Belfast = 141")
        assertEquals(605, y15d09(input, false))
        assertEquals(982, y15d09(input, true))
    }

    @Test
    @DisplayName("year 2015, day 10")
    fun year2015day10() {
        assertEquals("312211", y15d10("1", 5))
    }

    @Test
    @DisplayName("year 2015, day 11")
    fun year2015day11() {
        assertFalse(checkPassword("hijklmmn"))
        assertFalse(checkPassword("abbceffg"))
        assertFalse(checkPassword("abbcegjk"))
        assertTrue(checkPassword("abcdffaa"))
        assertTrue(checkPassword("ghjaabcc"))
    }

    @Test
    @DisplayName("year 2015, day 12")
    fun year2015day12() {
        assertEquals(6, y15d12("[1,2,3]", false))
        assertEquals(6, y15d12("""{"a":2,"b":4}""", false))
        assertEquals(3, y15d12("[[[3]]]", false))
        assertEquals(3, y15d12("""{"a":{"b":4},"c":-1}""", false))
        assertEquals(0, y15d12("""{"a":[-1,1]}""", false))
        assertEquals(0, y15d12("""[-1,{"a":1}]""", false))
        assertEquals(0, y15d12("[]", false))
        assertEquals(0, y15d12("{}", false))
        assertEquals(6, y15d12("[1,2,3]", true))
        assertEquals(4, y15d12("""[1,{"c":"red","b":2},3]""", true))
        assertEquals(0, y15d12("""{"d":"red","e":[1,2,3,4],"f":5}""", true))
        assertEquals(6, y15d12("""[1,"red",5]""", true))
    }

    @Test
    @DisplayName("year 2015, day 13")
    fun year2015day13() {
        val input = listOf(
            "Alice would gain 54 happiness units by sitting next to Bob.",
            "Alice would lose 79 happiness units by sitting next to Carol.",
            "Alice would lose 2 happiness units by sitting next to David.",
            "Bob would gain 83 happiness units by sitting next to Alice.",
            "Bob would lose 7 happiness units by sitting next to Carol.",
            "Bob would lose 63 happiness units by sitting next to David.",
            "Carol would lose 62 happiness units by sitting next to Alice.",
            "Carol would gain 60 happiness units by sitting next to Bob.",
            "Carol would gain 55 happiness units by sitting next to David.",
            "David would gain 46 happiness units by sitting next to Alice.",
            "David would lose 7 happiness units by sitting next to Bob.",
            "David would gain 41 happiness units by sitting next to Carol."
        )

        assertEquals(330, y15d13(input, false))
    }

    @Test
    @DisplayName("year 2015, day 17")
    fun year2015day17() {
        val input = listOf(20, 15, 10, 5, 5)
        val containers = getContainers(input, 25)
        assertEquals(4, y15d17(containers, false))
        assertEquals(3, y15d17(containers, true))
    }

    @Test
    @DisplayName("year 2015, day 19")
    fun year2015day19() {
        var replacements = listOf("H => HO", "H => OH", "O => HH")
        var molecule = "HOH"
        assertEquals(4, y15d19p01(replacements, molecule))

        replacements = listOf("e => H", "e => O", "H => HO", "H => OH", "O => HH")
        molecule = "HOHOHO"
        assertEquals(6, y15d19p02(replacements, molecule))
    }

    @Test
    @DisplayName("year 2015, day 20")
    fun year2015day20() {
        assertEquals(1, y15d20(10, false))
        assertEquals(2, y15d20(30, false))
        assertEquals(3, y15d20(40, false))
        assertEquals(4, y15d20(70, false))
        assertEquals(5, y15d20(60, false))
        assertEquals(6, y15d20(120, false))
        assertEquals(7, y15d20(80, false))
        assertEquals(8, y15d20(150, false))
        assertEquals(9, y15d20(130, false))
    }

    @Test
    @DisplayName("year 2015, day 23")
    fun year2015day23() {
        val input = listOf("inc a", "jio a, +2", "tpl a", "inc a")
        assertEquals(2, y15d23(input, false)[0])
    }

    @Test
    @DisplayName("year 2015, day 24")
    fun year2015day24() {
        val input = listOf(1, 2, 3, 4, 5, 7, 8, 9, 10, 11)
        assertEquals(y15d24(input, false), 99)
        assertEquals(y15d24(input, true), 44)
    }

    @Test
    @DisplayName("year 2015, day 25")
    fun year2015day25() {
        val codes = longArrayOf(
            31916031L, 18749137L, 16080970L, 21629792L, 17289845L, 24592653L, 8057251L,
            16929656L, 30943339L, 77061L, 32451966L, 1601130L, 7726640L, 10071777L, 33071741L, 17552253L,
            21345942L, 7981243L, 15514188L, 33511524L
        )

        var code = 20151125L
        codes.forEach {
            code = generateNextCode(code)
            assertEquals(it, code)
        }

        var input = Pair(6, 6)
        assertEquals(27995004L, y15d25(input))

        input = Pair(2, 4)
        assertEquals(7726640L, y15d25(input))
    }

    @Test
    @DisplayName("year 2016, day 01")
    fun year2016day01() {
        assertEquals(5, y16d01p01("R2, L3"))
        assertEquals(2, y16d01p01("R2, R2, R2"))
        assertEquals(12, y16d01p01("R5, L5, R5, R3"))
        assertEquals(4, y16d01p02("R8, R4, R4, R8"))
    }

    @Test
    @DisplayName("year 2016, day 02")
    fun year2016day02() {
        val input = listOf("ULL", "RRDDD", "LURDL", "UUUUD")
        assertEquals("1985", y16d02(input, false))
        assertEquals("5DB3", y16d02(input, true))
    }

    @Test
    @DisplayName("year 2016, day 03")
    fun year2016day03() {
        var input = listOf("5 10 25", "3 4 5", "12 10 17")
        assertEquals(2, y16d03p01(input))

        input = listOf("101 301 501", "102 302 502", "103 303 503", "201 401 601", "202 402 602", "203 403 603")
        assertEquals(6, y16d03p02(input))
    }

    @Test
    @DisplayName("year 2016, day 04")
    fun year2016day04() {
        val input = listOf(
            "aaaaa-bbb-z-y-x-123[abxyz]", "a-b-c-d-e-f-g-h-987[abcde]",
            "not-a-real-room-404[oarel]", "totally-real-room-200[decoy]"
        )
        assertEquals(1514, y16d04p01(input))
    }

    @Test
    @DisplayName("year 2016, day 05")
    fun year2016day05() {
        val input = "abc"
        assertEquals("18f47a30", y16d05p01(input))
        assertEquals("05ace8e3", y16d05p02(input))
    }

    @Test
    @DisplayName("year 2016, day 06")
    fun year2016day06() {
        val input = listOf(
            "eedadn", "drvtee", "eandsr", "raavrd", "atevrs", "tsrnev", "sdttsa", "rasrtv",
            "nssdts", "ntnada", "svetve", "tesnvt", "vntsnd", "vrdear", "dvrsen", "enarar"
        )
        assertEquals("easter", y16d06(input, false))
        assertEquals("advent", y16d06(input, true))
    }

    @Test
    @DisplayName("year 2016, day 07")
    fun year2016day07() {
        var input = listOf("abba[mnop]qrst", "abcd[bddb]xyyx", "aaaa[qwer]tyui", "ioxxoj[asdfgh]zxcvbn")
        assertEquals(2, y16d07(input, false))

        input = listOf("aba[bab]xyz", "xyx[xyx]xyx", "aaa[kek]eke", "zazbz[bzb]cdb")
        assertEquals(3, y16d07(input, true))
    }

    @Test
    @DisplayName("year 2016, day 08")
    fun year2016day08() {
        val screen = createScreen(7, 3)
        val input = listOf("rect 3x2", "rotate column x=1 by 1", "rotate row y=0 by 4", "rotate column x=1 by 1")
        assertEquals(6, y16d08p01(screen, input))
    }

    @Test
    @DisplayName("year 2016, day 09")
    fun year2016day09() {
        assertEquals(6, y16d09p01("ADVENT"))
        assertEquals(7, y16d09p01("A(1x5)BC"))
        assertEquals(9, y16d09p01("(3x3)XYZ"))
        assertEquals(11, y16d09p01("A(2x2)BCD(2x2)EFG"))
        assertEquals(6, y16d09p01("(6x1)(1x3)A"))
        assertEquals(18, y16d09p01("X(8x2)(3x3)ABCY"))
        assertEquals(6, y16d09p02("ADVENT"))
        assertEquals(9, y16d09p02("(3x3)XYZ"))
        assertEquals(20, y16d09p02("X(8x2)(3x3)ABCY"))
        assertEquals(241920, y16d09p02("(27x12)(20x12)(13x14)(7x10)(1x12)A"))
        assertEquals(445, y16d09p02("(25x3)(3x3)ABC(2x3)XY(5x2)PQRSTX(18x9)(3x2)TWO(5x7)SEVEN"))
    }

    @Test
    @DisplayName("year 2016, day 10")
    fun year2016day10() {
        val input = listOf(
            "value 5 goes to bot 2", "bot 2 gives low to bot 1 and high to bot 0",
            "value 3 goes to bot 1", "bot 1 gives low to output 1 and high to bot 0",
            "bot 0 gives low to output 2 and high to output 0", "value 2 goes to bot 2"
        ).toMutableList()
        val valuesToCompare = listOf(2, 5)
        assertEquals(2, y16d10(input, valuesToCompare, false))
    }
}
