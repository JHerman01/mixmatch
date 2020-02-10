import javax.swing.*;

/**
 *
 * @author Jeremy Herman
 * For CS 338 GUI Class
 *
 * This is the MixIcons enum.
 * It is home to any/all of the images that will be used for matching.
 *
 */

public enum MixIcons {
    AXOLOTL("axolotl.png"),
    BENGAL("bengal.png"),
    BRONCO("bronco.png"),
    CANARY("canary.png"),
    CHEETAH("cheetah.png"),
    DRAGON("dragon.png"),
    EAGLE( "eagle.png"),
    FINCH("finch.png"),
    GECKO("gecko.png"),
    HARPY("harpy.png"),
    IGUANA( "iguana.png"),
    JAGUAR( "jaguar.png"),
    KANGAROO("kangaroo.png"),
    LEMUR("lemur.png"),
    LEOPARD("leopard.png"),
    LION("lion.png"),
    MAGPIE("magpie.png"),
    NARWHAL("narwhal.png"),
    OCELOT("ocelot.png"),
    PANDA("panda.png"),
    PANTHER("panther.png"),
    QUAIL("quail.png"),
    RABBIT("rabbit.png"),
    SLOTH("sloth.png"),
    TAPIR( "tapir.png"),
    TIGER("tiger.png"),
    UAKARI("uakari.png"),
    VIPER( "viper.png"),
    WALRUS("walrus.png"),
    XENARTHRANS("xenarthrans.png"),
    YAK("yak.png"),
    ZEBRA("zebra.png");

    final ImageIcon image;

    MixIcons(String filename) {
        MixMatchModules mmm = new MixMatchModules();
        image = new ImageIcon(mmm.getImage(filename));
    }

}
