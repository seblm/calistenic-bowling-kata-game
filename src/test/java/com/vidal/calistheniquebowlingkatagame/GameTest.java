package com.vidal.calistheniquebowlingkatagame;

import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GameTest {

    private Game game = new Game();

    @Test
    public void should_score_zero_if_no_pinfall() {

        assertThat(game.score()).isEqualTo(new Score(new PinfallNumber(0)));
    }

    @Test
    public void should_score_one_if_one_pinfall() {
        Game oneRollGame = game.roll(new PinfallNumber(1));

        assertThat(oneRollGame.score()).isEqualTo(new Score(new PinfallNumber(1)));
    }

    @Test
    public void should_add_up_scores_after_two_rolls() {
        Game twoRollGame = game.roll(new PinfallNumber(1)).roll(new PinfallNumber(2));

        assertThat(twoRollGame.score()).isEqualTo(new Score(new PinfallNumber(3)));
    }

    @Test
    public void should_count_spare() {
        Game spareGame = game.roll(new PinfallNumber(6))
                .roll(new PinfallNumber(4))
                .roll(new PinfallNumber(1));

        assertThat(spareGame.score()).isEqualTo(new Score(new PinfallNumber(((6 + 4) + 1) + 1)));
    }

    @Test
    public void should_count_strike() {
        Game spareGame = game.roll(new PinfallNumber(10))
                .roll(new PinfallNumber(1))
                .roll(new PinfallNumber(2));

        assertThat(spareGame.score()).isEqualTo(new Score(new PinfallNumber(((10) + (1 + 2)) + (1 + 2))));
    }
}
