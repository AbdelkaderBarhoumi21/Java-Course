package design_patterns.adapter;

/*
 * Adapter Pattern
 * ---------------
 * Translates one interface into another the client expects.
 * "Plug" between two incompatible APIs.
 *
 *   Client → MediaPlayer (expected)
 *               ▲
 *               │ implements
 *           Mp4Adapter ──delegates──▶ LegacyMp4Player (incompatible API)
 *
 * When to use:
 *   - Reuse a third-party / legacy class whose API doesn't match yours.
 *   - You can't (or don't want to) change either side.
 */
public class AdapterExample {

    public static void main(String[] args) {
        MediaPlayer player = new Mp4Adapter(new LegacyMp4Player());
        player.play("song.mp4");
    }
}

// Target interface the client uses
interface MediaPlayer {
    void play(String file);
}

// Incompatible existing class — different method name & signature
class LegacyMp4Player {
    void startMp4(String filename) {
        System.out.println("Legacy player started: " + filename);
    }
}

// Adapter — speaks MediaPlayer, delegates to LegacyMp4Player
class Mp4Adapter implements MediaPlayer {
    private final LegacyMp4Player legacy;

    public Mp4Adapter(LegacyMp4Player legacy) {
        this.legacy = legacy;
    }

    @Override
    public void play(String file) {
        legacy.startMp4(file);
    }
}
