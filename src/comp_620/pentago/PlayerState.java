package src.comp_620.pentago;

public enum PlayerState {
    BLACK_PIECE {
        @Override
        public int playerToInt() {
            return 0;
        }
    },
    WHITE_PIECE {
        @Override
        public int playerToInt() {
            return 1;
        }
    };

    public abstract int playerToInt();
}
