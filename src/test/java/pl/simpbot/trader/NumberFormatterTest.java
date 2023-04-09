package pl.simpbot.trader;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


class NumberFormatterTest {
    NumberFormatter numberFormatter = new NumberFormatter();

    @Test
    public void should_format_decimal_when_step_smaller_then_1() {
        //given
        String step = "0.001";
        Double toFormat = 123.123456;

        //when
        String result = numberFormatter.formatDecimals(step, toFormat);

        //then
        assertThat(result).isEqualTo("123.123");
    }

    @Test
    public void should_format_decimal_when_step_smaller_then_1_() {
        //given
        String step = "0.1";
        Double toFormat = 123.123456;

        //when
        String result = numberFormatter.formatDecimals(step, toFormat);

        //then
        assertThat(result).isEqualTo("123.1");
    }

    @Test
    public void should_format_decimal_when_step_bigger_then_1() {
        //given
        String step = "10";
        Double toFormat = 1234.123456;

        //when
        String result = numberFormatter.formatGraterThan1(step, toFormat);

        //then
        assertThat(result).isEqualTo("1230");
    }

    @Test
    public void should_format_decimal_when_step_bigger_then_1_() {
        //given
        String step = "100";
        Double toFormat = 1234.123456;

        //when
        String result = numberFormatter.formatGraterThan1(step, toFormat);

        //then
        assertThat(result).isEqualTo("1200");
    }

    @Test
    public void should_format_decimal_when_step_equal_to_1_() {
        //given
        String step = "1";
        Double toFormat = 1234.123456;

        //when
        String result = numberFormatter.formatGraterThan1(step, toFormat);

        //then
        assertThat(result).isEqualTo("1234");
    }

}