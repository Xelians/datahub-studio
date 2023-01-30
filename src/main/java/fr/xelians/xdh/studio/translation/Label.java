package fr.xelians.xdh.studio.translation;

/**
 * The class Label is used to translate label in French and English for worker forms
 **/

/**
 * @author aurelien on 02/06/2022
 **/
public final class Label {

	private Label() {
	}

	/**
	 * Create a Translation object
	 * @param fr the french label
	 * @param en the english label
	 * @return {{@link Translation}}
	 */
	public static Translation of(String fr, String en){
		return new Translation(fr, en);
	}

	/**
	 * Create a Translation object with same label for each language
	 * @param label the label
	 * @return {{@link Translation}}
	 */
	public static Translation of(String label){
		return new Translation(label, label);
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
