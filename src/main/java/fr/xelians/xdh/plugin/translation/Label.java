package fr.xelians.xdh.plugin.translation;

/**
 * @author aurelien on 02/06/2022
 */
public final class Label {

	private Label() {
	}

	public static Translation of(String fr, String en){
		return new Translation(fr, en);
	}

	public static class Translation{

		private String fr;
		private String en;

		private Translation(String fr, String en) {
			this.fr = fr;
			this.en = en;
		}

		public String getFr() {
			return fr;
		}

		public String getEn() {
			return en;
		}
	}
}
