package utils;

public enum Operator {

    ADD, SUB, MUL, DIV, CONCAT;

    public long execute(long a, long b) {
        return switch(this) {
            case ADD -> a + b;
            case SUB -> a - b;
            case MUL -> a * b;
            case DIV -> a / b;
            case CONCAT -> concatenate(a, b);
        };
    }

    private long concatenate(long a, long b) {
        a *= (long) Math.pow(10, Math.ceil(Math.log10(b)));
        return a + b;
    }
}
