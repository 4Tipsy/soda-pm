

package _4Tipsy.SodaCli.utils





fun getTermWidth(): Int {

  val COLUMNS_value = System.getenv("COLUMNS")

  if (COLUMNS_value != null ) { return COLUMNS_value.toInt() }
  else { return 80 }

}