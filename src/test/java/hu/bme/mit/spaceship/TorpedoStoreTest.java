package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TorpedoStoreTest {

  @Test
  void fire_Success() {
    TorpedoStore store = new TorpedoStore(1, 0.0);

    boolean result = store.fire(1);

    assertTrue(result);
    assertEquals(0, store.getTorpedoCount());
  }

  @Test
  void fire_DecreasesCountByRequestedAmount() {
    TorpedoStore store = new TorpedoStore(5, 0.0);

    assertTrue(store.fire(2));

    assertEquals(3, store.getTorpedoCount());
    assertFalse(store.isEmpty());
  }

  @Test
  void fire_Failure_KeepsTorpedoCount() {
    TorpedoStore store = new TorpedoStore(3, 1.0);

    boolean result = store.fire(1);

    assertFalse(result);
    assertEquals(3, store.getTorpedoCount());
  }

  @Test
  void isEmpty_TrueWhenNoTorpedos() {
    TorpedoStore store = new TorpedoStore(0, 0.0);

    assertTrue(store.isEmpty());
    assertEquals(0, store.getTorpedoCount());
  }

  @Test
  void isEmpty_TrueAfterLastTorpedoFired() {
    TorpedoStore store = new TorpedoStore(1, 0.0);

    store.fire(1);

    assertTrue(store.isEmpty());
  }

  @Test
  void fire_ZeroTorpedos_Throws() {
    TorpedoStore store = new TorpedoStore(5, 0.0);

    assertThrows(IllegalArgumentException.class, () -> store.fire(0));
  }

  @Test
  void fire_MoreThanAvailable_Throws() {
    TorpedoStore store = new TorpedoStore(1, 0.0);

    assertThrows(IllegalArgumentException.class, () -> store.fire(2));
    assertEquals(1, store.getTorpedoCount());
  }

  @Test
  void fire_EmptyStore_Throws() {
    TorpedoStore store = new TorpedoStore(0, 0.0);

    assertThrows(IllegalArgumentException.class, () -> store.fire(1));
  }
}