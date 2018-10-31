package ohtu;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisaysEiLisaa() {
        varasto.lisaaVarastoon(-1);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void ylitaysiLisaysTayttaa() {
        varasto.lisaaVarastoon(38);

        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenPoistoEiPoista() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(-1);

        assertEquals(5, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void liikaaPoistaminenTyhjentaa() {
        varasto.lisaaVarastoon(5);
        varasto.otaVarastosta(50);

        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void toStringKertooOlennaisen() {
        varasto.lisaaVarastoon(4);

        assertEquals("saldo = 4.0, vielä tilaa 6.0", varasto.toString());
    }

    @Test
    public void negatiivinenLuontiLuoNollanKokoisen() {
        Varasto v = new Varasto(-1);

        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void luontiAlkusaldollaAsettaaSaldon() {
        Varasto v = new Varasto(5, 4);

        assertEquals(4, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLuontiAlkusaldollaLuoNollanKokoisen() {
        Varasto v = new Varasto(-1, 5);

        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void ylitaydellaAlkusaldollaLuontiLuoTayden() {
        Varasto v = new Varasto(3, 5);

        assertEquals(3, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void negatiivisellaAlkusaldollaLuontiLuoTyhjan() {
        Varasto v = new Varasto(3, -2);

        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }
}
