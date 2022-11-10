import android.util.Log;

import java.util.Calendar;

public  class apiAlan {
    static String TAG ="API Alan";
    public static int GetToken()
    {int v = Calendar.MONTH + Calendar.DAY_OF_MONTH * Calendar.HOUR_OF_DAY;
int rem = v%7;
String retorno=String.valueOf(v)+ String.valueOf(rem);
        Log.d(TAG, retorno);
return Integer.parseInt(retorno);
    }
}
