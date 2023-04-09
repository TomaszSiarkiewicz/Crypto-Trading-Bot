package pl.simpbot.trader;

class NumberFormatter {
    String formatGraterThan1(String step, double value) {

        int stepInt = Integer.parseInt(step);
        int v = (((int) value / stepInt) * stepInt);
        return "" + v;
    }

    String formatEqualTo1(Long value) {
        return "" + value;
    }

    String formatDecimals(String size, Double value) {
        String[] split = value.toString().split("\\.");
        int decimalsExpected = size.length() - 2;

        if (noDecimals(split)) {
            return split[0];

        } else if (decimalsInRange(split, decimalsExpected)) {
            return split[0] + "." + split[1];

        } else {
            return split[0] + "." + split[1].substring(0, decimalsExpected);
        }
    }

    private boolean decimalsInRange(String[] split, int decimalsExpected) {
        return split[1].length() <= decimalsExpected;
    }

    private boolean noDecimals(String[] split) {
        return split.length == 1;
    }
}
