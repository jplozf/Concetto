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
		html =  html +  "<tr><th>Version</th><th>Année</th><th>Mots</th></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS2</b></td><td align=\"center\">1994</td><td align=\"center\">353 526</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS3</b></td><td align=\"center\">1999</td><td align=\"center\">364 370</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS4</b></td><td align=\"center\">2004</td><td align=\"center\">369 085</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS5</b></td><td align=\"center\">2008</td><td align=\"center\">378 989</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS6</b></td><td align=\"center\">2012</td><td align=\"center\">386 264</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS7</b></td><td align=\"center\">2016</td><td align=\"center\">393 670</td></tr>";
		html =  html +  "<tr><td align=\"center\"><b>ODS8</b></td><td align=\"center\">2020</td><td align=\"center\">411 430</td></tr>";
		html =  html +  "</table></center><br><br>";
		//
		html =  html +  "<center><i><b>Glossaire des termes utilisés au SCRABBLE&reg;</b></i></center>";
		html =  html +  "<ul>";
		html =  html +  "<li><b>Benjamin</b> : prolongement d'un mot posé sur la grille de trois lettres par l'avant afin d'en former un autre, généralement pour atteindre une case « mot compte triple ». Par exemple avec VENIR, posé en H4 sur la grille, on pourrait envisager CONVENIR, PARVENIR ou SURVENIR.</li>";
		html =  html +  "<li><b>Beaufort</b> : coup technique qui consiste à tripler des lettres semi-chères pour obtenir plus qu’en doublant le mot.</li>";
		html =  html +  "<li><b>Blanchard</b> : scrabble marquant peu de points, car ne passant sur aucune case multiplicatrice.</li>";
		html =  html +  "<li><b>Caramel</b> : nom affectif donné aux jetons.</li>";
		html =  html +  "<li><b>Case</b> : un des 225 emplacements de la grille sur lesquels on place les lettres. Chaque case est identifiée par une référence alphanumérique.</li>";
		html =  html +  "<li><b>Cheminée</b> : collante dans laquelle les lettres sont placées « en sandwich » entre deux mots déjà posés sur la grille.</li>";
		html =  html +  "<li><b>Collante</b> : coup consistant à placer un mot le long d'un autre mot déjà posé sur la grille, formant alors de nouveaux mots, généralement de 2 lettres, dans l'autre sens.</li>";
		html =  html +  "<li><b>Grille</b> : le plateau de jeu de Scrabble, formé d'une grille de 225 (15 x 15) cases. Les colonnes sont référencées de 1 à 15 et les lignes de A à O. Elle peut être ouverte (les mots placés offrent de nombreuses possibilités de jeu) ou fermée (peu d'opportunités sont à la disposition du joueur).</li>";
		html =  html +  "<li><b>Joker</b> : la lettre blanche qui peut représenter n'importe quelle lettre.</li>";
		html =  html +  "<li><b>Legendre</b> : coup consistant à multiplier, d'une part, deux ou trois fois une lettre chère (ou semi-chère) par le placement sur une case bleu clair ou bleu foncé, et d'autre part à multiplier le mot par deux par le placement d'une des lettres sur une case rose.</li>";
		html =  html +  "<li><b>Lettre</b> : se dit des 26 lettres, ou des 102 jetons.</li>";
		html =  html +  "<li><b>Lettre blanche</b> : voir Joker.</li>";
		html =  html +  "<li><b>Lettre chère</b> : se dit des sept lettres qui ont une valeur supérieure à quatre points. Il s'agit de J et Q (8 points); K, W, X, Y et Z (10 points).</li>";
		html =  html +  "<li><b>Lettre semi-chère</b> : se dit d'une lettre qui a une valeur de deux, trois ou quatre points. En français, D, G, M (2 points), B, C, P (3 points), F, H, V (4 points).</li>";
		html =  html +  "<li><b>Maçonnerie</b> : coup formant plusieurs petits mots, le plus souvent en posant peu de lettres.</li>";
		html =  html +  "<li><b>Nonuple</b> : mot d'au moins huit lettres reliant deux cases « mot compte triple ». La valeur du mot est alors multipliée par neuf.</li>";
		html =  html +  "<li><b>ODS</b> : L'Officiel du Scrabble est le dictionnaire officiel du jeu de SCRABBLE&reg; francophone depuis le 1er janvier 1990. Il est édité par Larousse.</li>";
		html =  html +  "<li><b>Passer</b> : ne pas jouer de mot sur son tour. Le joueur marque alors zéro point.</li>";
		html =  html +  "<li><b>Pivot</b> : une lettre pivot est une lettre placée sur une case bleue à l'angle de deux nouveaux mots. Sa valeur est ainsi multipliée deux fois, verticalement et horizontalement.</li>";
		html =  html +  "<li><b>Quadruple</b> : mot d'au moins sept lettres reliant deux cases « mot compte double ». La valeur du mot est alors multipliée par quatre.</li>";
		html =  html +  "<li><b>Rallonge</b> : action de rallonger un mot en plaçant au moins une lettre au début ou à la fin du mot actuel. Par exemple REVENIR → PRÉVENIR, JOUER → JOUERA, PARLER → PARLERONT.</li>";
		html =  html +  "<li><b>Reliquat</b> : ensemble des lettres restant sur le chevalet ou dans le sac.</li>";
		html =  html +  "<li><b>Référence alphanumérique</b> : une référence pour décrire le placement d'un mot, qui consiste en une lettre de A à O et un nombre de 1 à 15. La case en haut à gauche a comme référence A1, et celle en bas à droite a comme référence O15. Pour indiquer un mot placé horizontalement, on commence par la lettre (exemple « C3 ») et pour indiquer un mot placé verticalement, on commence par le chiffre (exemple « 3C »).</li>";
		html =  html +  "<li><b>Scrabble</b> : coup consistant à former un mot en utilisant ses sept lettres, octroyant une bonification de 50 points.</li>";
		html =  html +  "<li><b>Sec</b> : se dit d'un Scrabble composé uniquement des sept lettres du tirage.</li>";
		html =  html +  "<li><b>Top</b> : mot dont le score est le plus élevé sur un coup.</li>";
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