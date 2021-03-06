package fr.ozf.concetto;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;

public class FragmentAbout extends Fragment {

    //***********************************************************************
    // FragmentAbout()
    //***********************************************************************
    public FragmentAbout() {
        // Required empty public constructor
    }

    //***********************************************************************
    // newInstance()
    //***********************************************************************
    public static FragmentAbout newInstance() {
        return (new FragmentAbout());
    }

    @Override
    //***********************************************************************
    // onCreateView()
    //***********************************************************************
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View vw = inflater.inflate(R.layout.fragment_about, container, false);
        showInfo(vw);
        return vw;
    }

    //***********************************************************************
    // showInfo()
    //***********************************************************************
    private void showInfo(View vw)
    {
        String pattern = "yyyyMMdd.HHmmss";
        @SuppressLint("SimpleDateFormat") SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        // String buildString = simpleDateFormat.format(buildDate);
        //
        String html = "<html>";
        html =  html +  "<center><table>";
        html =  html +  "<tr><td rowspan=\"3\" align=\"center\" bgcolor=\"#b3c3dd\"><font color=\"#ffffff\" size=\"4\"><b>CONCETTO</b></font></td><td align=\"center\">ODS consulting & gaming</td></tr>";
        html =  html +  "<tr><td align=\"center\">for Android systems</td></tr>";
		// html =  html +  "<tr><td>Build " + buildString + "</td></tr>";
		html =  html +  "<tr><td colspan=\"2\" align=\"center\"><font color=\"#b3c3dd\">www.ozf.fr/#concetto</font></td></tr>";
		html =  html +  "<tr><td colspan=\"2\" align=\"center\">&nbsp;</td></tr>";
		html =  html +  "<tr><td colspan=\"2\" align=\"center\"><i>&copy; J.-P. Liguori 2021 - All rights reserved</i></td></tr>";
		html =  html +  "</table></center><br><br>";

		html =  html +  "<center><i><b>Nombre et valeur des lettres</b></i></center><br>";
		html =  html +  "<center><table border=\"1\">";
		html =  html +  "<tr><th>Lettre</th><th>Nombre</th><th>Valeur</th><th>Lettre</th><th>Nombre</th><th>Valeur</th></tr>";
		html =  html +  "<tr><td align=\"center\"><b>A</b></td><td align=\"center\">9</td><td align=\"center\">1</td><td align=\"center\"><b>B</b></td><td align=\"center\">2</td><td align=\"center\">3</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>C</b></td><td align=\"center\">2</td><td align=\"center\">3</td><td align=\"center\"><b>D</b></td><td align=\"center\">3</td><td align=\"center\">2</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>E</b></td><td align=\"center\">15</td><td align=\"center\">1</td><td align=\"center\"><b>F</b></td><td align=\"center\">2</td><td align=\"center\">4</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>G</b></td><td align=\"center\">2</td><td align=\"center\">2</td><td align=\"center\"><b>H</b></td><td align=\"center\">2</td><td align=\"center\">4</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>I</b></td><td align=\"center\">8</td><td align=\"center\">1</td><td align=\"center\"><b>J</b></td><td align=\"center\">1</td><td align=\"center\">8</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>K</b></td><td align=\"center\">1</td><td align=\"center\">10</td><td align=\"center\"><b>L</b></td><td align=\"center\">5</td><td align=\"center\">1</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>M</b></td><td align=\"center\">3</td><td align=\"center\">2</td><td align=\"center\"><b>N</b></td><td align=\"center\">6</td><td align=\"center\">1</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>O</b></td><td align=\"center\">6</td><td align=\"center\">1</td><td align=\"center\"><b>P</b></td><td align=\"center\">2</td><td align=\"center\">3</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>Q</b></td><td align=\"center\">1</td><td align=\"center\">8</td><td align=\"center\"><b>R</b></td><td align=\"center\">6</td><td align=\"center\">1</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>S</b></td><td align=\"center\">6</td><td align=\"center\">1</td><td align=\"center\"><b>T</b></td><td align=\"center\">6</td><td align=\"center\">1</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>U</b></td><td align=\"center\">6</td><td align=\"center\">1</td><td align=\"center\"><b>V</b></td><td align=\"center\">2</td><td align=\"center\">4</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>W</b></td><td align=\"center\">1</td><td align=\"center\">10</td><td align=\"center\"><b>X</b></td><td align=\"center\">1</td><td align=\"center\">10</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>Y</b></td><td align=\"center\">1</td><td align=\"center\">10</td><td align=\"center\"><b>Z</b></td><td align=\"center\">1</td><td align=\"center\">10</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>&nbsp;</b></td><td align=\"center\">2</td><td align=\"center\">0</td><td align=\"center\" colspan=\"4\"><i>(joker)</i></td></tr>";
		html =  html +  "</table></center><br><br>";
		//
		html =  html +  "<center><i><b>Nombre de mots de l'ODS</b></i></center><br>";
		html =  html +  "<center><table border=\"1\">";
		html =  html +  "<tr><th>Version</th><th>Ann??e</th><th>Mots</th></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS2</b></td><td align=\"center\">1994</td><td align=\"center\">353 526</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS3</b></td><td align=\"center\">1999</td><td align=\"center\">364 370</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS4</b></td><td align=\"center\">2004</td><td align=\"center\">369 085</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS5</b></td><td align=\"center\">2008</td><td align=\"center\">378 989</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS6</b></td><td align=\"center\">2012</td><td align=\"center\">386 264</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS7</b></td><td align=\"center\">2016</td><td align=\"center\">393 670</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS8</b></td><td align=\"center\">2020</td><td align=\"center\">411 430</td></tr>";
		html =  html +  "</table></center><br><br>";
		//
		html =  html +  "<center><i><b>Glossaire des termes utilis??s au SCRABBLE&reg;</b></i></center>";
		html =  html +  "<ul>";
		html =  html +  "<li><b>Benjamin</b> : prolongement d'un mot pos?? sur la grille de trois lettres par l'avant afin d'en former un autre, g??n??ralement pour atteindre une case ?? mot compte triple ??. Par exemple avec VENIR, pos?? en H4 sur la grille, on pourrait envisager CONVENIR, PARVENIR ou SURVENIR.</li>";
		html =  html +  "<li><b>Beaufort</b> : coup technique qui consiste ?? tripler des lettres semi-ch??res pour obtenir plus qu???en doublant le mot.</li>";
		html =  html +  "<li><b>Blanchard</b> : scrabble marquant peu de points, car ne passant sur aucune case multiplicatrice.</li>";
		html =  html +  "<li><b>Caramel</b> : nom affectif donn?? aux jetons.</li>";
		html =  html +  "<li><b>Case</b> : un des 225 emplacements de la grille sur lesquels on place les lettres. Chaque case est identifi??e par une r??f??rence alphanum??rique.</li>";
		html =  html +  "<li><b>Chemin??e</b> : collante dans laquelle les lettres sont plac??es ?? en sandwich ?? entre deux mots d??j?? pos??s sur la grille.</li>";
		html =  html +  "<li><b>Collante</b> : coup consistant ?? placer un mot le long d'un autre mot d??j?? pos?? sur la grille, formant alors de nouveaux mots, g??n??ralement de 2 lettres, dans l'autre sens.</li>";
		html =  html +  "<li><b>Grille</b> : le plateau de jeu de Scrabble, form?? d'une grille de 225 (15 x 15) cases. Les colonnes sont r??f??renc??es de 1 ?? 15 et les lignes de A ?? O. Elle peut ??tre ouverte (les mots plac??s offrent de nombreuses possibilit??s de jeu) ou ferm??e (peu d'opportunit??s sont ?? la disposition du joueur).</li>";
		html =  html +  "<li><b>Joker</b> : la lettre blanche qui peut repr??senter n'importe quelle lettre.</li>";
		html =  html +  "<li><b>Legendre</b> : coup consistant ?? multiplier, d'une part, deux ou trois fois une lettre ch??re (ou semi-ch??re) par le placement sur une case bleu clair ou bleu fonc??, et d'autre part ?? multiplier le mot par deux par le placement d'une des lettres sur une case rose.</li>";
		html =  html +  "<li><b>Lettre</b> : se dit des 26 lettres, ou des 102 jetons.</li>";
		html =  html +  "<li><b>Lettre blanche</b> : voir Joker.</li>";
		html =  html +  "<li><b>Lettre ch??re</b> : se dit des sept lettres qui ont une valeur sup??rieure ?? quatre points. Il s'agit de J et Q (8 points); K, W, X, Y et Z (10 points).</li>";
		html =  html +  "<li><b>Lettre semi-ch??re</b> : se dit d'une lettre qui a une valeur de deux, trois ou quatre points. En fran??ais, D, G, M (2 points), B, C, P (3 points), F, H, V (4 points).</li>";
		html =  html +  "<li><b>Ma??onnerie</b> : coup formant plusieurs petits mots, le plus souvent en posant peu de lettres.</li>";
		html =  html +  "<li><b>Nonuple</b> : mot d'au moins huit lettres reliant deux cases ?? mot compte triple ??. La valeur du mot est alors multipli??e par neuf.</li>";
		html =  html +  "<li><b>ODS</b> : L'Officiel du Scrabble est le dictionnaire officiel du jeu de SCRABBLE&reg; francophone depuis le 1er janvier 1990. Il est ??dit?? par Larousse.</li>";
		html =  html +  "<li><b>Passer</b> : ne pas jouer de mot sur son tour. Le joueur marque alors z??ro point.</li>";
		html =  html +  "<li><b>Pivot</b> : une lettre pivot est une lettre plac??e sur une case bleue ?? l'angle de deux nouveaux mots. Sa valeur est ainsi multipli??e deux fois, verticalement et horizontalement.</li>";
		html =  html +  "<li><b>Quadruple</b> : mot d'au moins sept lettres reliant deux cases ?? mot compte double ??. La valeur du mot est alors multipli??e par quatre.</li>";
		html =  html +  "<li><b>Rallonge</b> : action de rallonger un mot en pla??ant au moins une lettre au d??but ou ?? la fin du mot actuel. Par exemple REVENIR ??? PR??VENIR, JOUER ??? JOUERA, PARLER ??? PARLERONT.</li>";
		html =  html +  "<li><b>Reliquat</b> : ensemble des lettres restant sur le chevalet ou dans le sac.</li>";
		html =  html +  "<li><b>R??f??rence alphanum??rique</b> : une r??f??rence pour d??crire le placement d'un mot, qui consiste en une lettre de A ?? O et un nombre de 1 ?? 15. La case en haut ?? gauche a comme r??f??rence A1, et celle en bas ?? droite a comme r??f??rence O15. Pour indiquer un mot plac?? horizontalement, on commence par la lettre (exemple ?? C3 ??) et pour indiquer un mot plac?? verticalement, on commence par le chiffre (exemple ?? 3C ??).</li>";
		html =  html +  "<li><b>Scrabble</b> : coup consistant ?? former un mot en utilisant ses sept lettres, octroyant une bonification de 50 points.</li>";
		html =  html +  "<li><b>Sec</b> : se dit d'un Scrabble compos?? uniquement des sept lettres du tirage.</li>";
		html =  html +  "<li><b>Top</b> : mot dont le score est le plus ??lev?? sur un coup.</li>";
		html =  html +  "</ul><br>";
		//
		html =  html +  "<center><font color=\"#b3c3dd\"><b>This application is not affiliated, endorsed or sponsored by SCRABBLE&reg; or Larousse.</b></font></center><br>";
		//
		html =  html +  "<p align=\"justify\"><i>SCRABBLE&reg; is a registered trademark. All intellectual property rights in and to the game are owned in the U.S.A and Canada by Hasbro Inc., and throughout the rest of the world by J.W. Spear & Sons Limited of Maidenhead, Berkshire, England, a subsidiary of Mattel Inc. Mattel and Spear are not affiliated with Hasbro.</i></p><br><br>";
		//
		html =  html +  "</html>";

        Log.d("WEB", html);

        WebView wv = (WebView) vw.findViewById(R.id.webInfo);
        wv.setBackgroundColor(getResources().getColor(R.color.Grullo));
        wv.loadDataWithBaseURL(null, html, "text/html; charset=utf-8", "UTF-8", null);
    }
}