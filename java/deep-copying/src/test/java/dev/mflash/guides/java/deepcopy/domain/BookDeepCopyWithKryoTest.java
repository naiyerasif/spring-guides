package dev.mflash.guides.java.deepcopy.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.esotericsoftware.kryo.Kryo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BookDeepCopyWithKryoTest {

  @Test
  @DisplayName("Should copy data but not reference")
  void shouldCopyDataButNotReference() {
    final var andyWeir = new Author();
    andyWeir.setName("Andy Weir");

    final var martian = new Book();
    martian.setTitle("The Martian");
    martian.setAuthor(andyWeir);

    final var kryo = new Kryo();
    kryo.setRegistrationRequired(false);
    final Book copyOfMartian = kryo.copy(martian);

    assertThat(copyOfMartian).isNotEqualTo(martian);
    assertThat(copyOfMartian.getAuthor()).isNotEqualTo(martian.getAuthor());
    assertThat(copyOfMartian.getTitle()).isEqualTo(martian.getTitle());
    assertThat(copyOfMartian.getAuthor().getName()).isEqualTo(martian.getAuthor().getName());

    copyOfMartian.getAuthor().setName("Marie Lu");
    assertThat(copyOfMartian.getAuthor().getName()).isNotEqualTo(martian.getAuthor().getName());
  }
}
