package org.densyakun.bukkit.dsp;
public class Calc{
	static String[]calcs0=new String[44];
	static String[]calcs1=new String[44];
	static String[]calcs2=new String[44];
	static String[]calcs3=new String[44];
	static String[]calcs4=new String[44];
	static String[]calcs5=new String[44];
	public static void Init(){
		calcs0[0]="A";
		calcs1[0]="あ";
		calcs0[1]="I";
		calcs1[1]="い";
		calcs0[2]="U";
		calcs1[2]="う";
		calcs0[3]="E";
		calcs1[3]="え";
		calcs0[4]="O";
		calcs1[4]="お";
		calcs0[5]="KA";
		calcs1[5]="か";
		calcs0[6]="KI";
		calcs1[6]="き";
		calcs0[7]="KU";
		calcs1[7]="く";
		calcs0[8]="KE";
		calcs1[8]="け";
		calcs0[9]="KO";
		calcs1[9]="こ";
		calcs0[10]="SA";
		calcs1[10]="さ";
		calcs0[11]="SI";
		calcs1[11]="し";
		calcs0[12]="SU";
		calcs1[12]="す";
		calcs0[13]="SE";
		calcs1[13]="せ";
		calcs0[14]="SO";
		calcs1[14]="そ";
		calcs0[15]="TA";
		calcs1[15]="た";
		calcs0[16]="TI";
		calcs1[16]="ち";
		calcs0[17]="TU";
		calcs1[17]="つ";
		calcs0[18]="TE";
		calcs1[18]="て";
		calcs0[19]="TO";
		calcs1[19]="と";
		calcs0[20]="NA";
		calcs1[20]="な";
		calcs0[21]="NI";
		calcs1[21]="に";
		calcs0[22]="NU";
		calcs1[22]="ぬ";
		calcs0[23]="NE";
		calcs1[23]="ね";
		calcs0[24]="NO";
		calcs1[24]="の";
		calcs0[25]="HA";
		calcs1[25]="は";
		calcs0[26]="HI";
		calcs1[26]="ひ";
		calcs0[27]="HU";
		calcs1[27]="ふ";
		calcs0[28]="HE";
		calcs1[28]="へ";
		calcs0[29]="HO";
		calcs1[29]="ほ";
		calcs0[30]="YA";
		calcs1[30]="や";
		calcs0[32]="YU";
		calcs1[32]="ゆ";
		calcs0[33]="YE";
		calcs1[33]="ヱ";
		calcs0[34]="YO";
		calcs1[34]="よ";
		calcs0[35]="RA";
		calcs1[35]="ら";
		calcs0[36]="RI";
		calcs1[36]="ら";
		calcs0[37]="RU";
		calcs1[37]="る";
		calcs0[38]="RE";
		calcs1[38]="れ";
		calcs0[39]="RO";
		calcs1[39]="ろ";
		calcs0[40]="WA";
		calcs1[40]="わ";
		calcs0[41]="WO";
		calcs1[41]="を";
		calcs0[42]="NN";
		calcs1[42]="ん";
		calcs0[43]="N";
		calcs1[43]="ん";
	}
	public static String calc(String str){
		String calc=str;
		for(int a=0;a<calcs0.length;a++){
			String[]sp=calc.split(calcs0[a]);
			if(sp[a]!=null||!calc.equals(sp[0])){
				calc="";
				calc=sp[0]+calcs1[a]+sp[1];
			}
		}
		return calc;
	}
}