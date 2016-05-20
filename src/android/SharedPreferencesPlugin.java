package name.ratson.cordova.plugin;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.json.JSONArray;
import org.json.JSONException;

public class SharedPreferencesPlugin extends CordovaPlugin {
    public static String prefFile = "";
    SharedPreferences sharedPref;

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if ("getSharedPreferences".equals(action)) {
            return getSharedPreferences(args, callbackContext);
        } else if ("putString".equals(action)) {
            return putStringPref(args, callbackContext);
        } else if ("getString".equals(action)) {
            return getStringPref(args, callbackContext);
        } else if ("putBoolean".equals(action)) {
            return putBoolPref(args, callbackContext);
        } else if ("getBoolean".equals(action)) {
            return getBoolPref(args, callbackContext);
        } else if ("putInt".equals(action)) {
            return putIntPref(args, callbackContext);
        } else if ("getInt".equals(action)) {
            return getIntPref(args, callbackContext);
        } else if ("putLong".equals(action)) {
            return putLongPref(args, callbackContext);
        } else if ("getLong".equals(action)) {
            return getLongPref(args, callbackContext);
        } else if ("remove".equals(action)) {
            return removePref(args, callbackContext);
        } else if ("clear".equals(action)) {
            return this.clearPrefs(callbackContext);
        } else {
            callbackContext.error("Invalid Action");
            return false;
        }
    }

    private boolean putBoolPref(JSONArray args, CallbackContext callbackContext) throws JSONException {
        Editor editor = sharedPref.edit();
        try {
            editor.putBoolean(args.getString(0), args.getBoolean(1));
            editor.commit();
        } catch (Exception e) {
            callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getBoolean(1) + e.getMessage());
            return false;
        }
        callbackContext.success("Added Value " + args.getBoolean(1) + " to Preferences key " + args.getString(0));
        return true;
    }

    private boolean getBoolPref(JSONArray args, CallbackContext callbackContext) throws JSONException {
        Boolean keyVal;
        try {
            if (sharedPref.contains(args.getString(0))) {
                keyVal = sharedPref.getBoolean(args.getString(0), false);
                if (keyVal.equals(true)) {
                    callbackContext.success(1);
                } else {
                    callbackContext.success(0);
                }
                return true;
            } else {
                callbackContext.error("No data");
                return false;
            }
        } catch (Exception e) {
            callbackContext.error("Could Not Retreive " + args.getString(0) + e.getMessage());
            return false;
        }
    }

    private boolean putIntPref(JSONArray args, CallbackContext callbackContext) throws JSONException {
        Editor editor = sharedPref.edit();
        try {
            editor.putInt(args.getString(0), args.getInt(1));
            editor.commit();
        } catch (Exception e) {
            callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getInt(1) + e.getMessage());
            return false;
        }
        callbackContext.success("Added Value " + args.getInt(1) + " to Preferences key " + args.getString(0));
        return true;
    }

    private boolean getIntPref(JSONArray args, CallbackContext callbackContext) throws JSONException {
        Integer KeyVal;
        try {
            if (sharedPref.contains(args.getString(0))) {
                KeyVal = sharedPref.getInt(args.getString(0), 0);
                callbackContext.success(KeyVal);
                return true;
            } else {
                callbackContext.error("No data");
                return false;
            }
        } catch (Exception e) {
            callbackContext.error("Could Not Retreive " + args.getString(0) + e.getMessage());
            return false;
        }
    }

    private boolean putLongPref(JSONArray args, CallbackContext callbackContext) throws JSONException {
        Editor editor = sharedPref.edit();
        try {
            editor.putLong(args.getString(0), args.getLong(1));
            editor.commit();
        } catch (Exception e) {
            callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getLong(1) + e.getMessage());
            return false;
        }
        callbackContext.success("Added Value " + args.getLong(1) + " to Preferences key " + args.getString(0));
        return true;
    }

    private boolean getLongPref(JSONArray args, CallbackContext callbackContext) throws JSONException {
        Long keyVal;
        try {
            if (sharedPref.contains(args.getString(0))) {
                keyVal = sharedPref.getLong(args.getString(0), 0);
                callbackContext.success(keyVal.toString());
                return true;
            } else {
                callbackContext.error("No data");
                return false;
            }
        } catch (Exception e) {
            callbackContext.error("Could Not Retreive " + args.getString(0) + e.getMessage());
            return false;
        }
    }

    private boolean getSharedPreferences(JSONArray args, CallbackContext callbackContext) throws JSONException {
        prefFile = args.getString(0);
        String modeType = args.getString(1);
        Context context = cordova.getActivity();
        if ("MODE_APPEND".equals(modeType)) {
            try {
                sharedPref = context.getSharedPreferences(prefFile, Context.MODE_APPEND);
            } catch (Exception e) {
                callbackContext.error("Error creating Shared Preferences" + e.getMessage());
                return false;
            }
            callbackContext.success("Shared Preferences Created");
            return true;
        } else if ("MODE_PRIVATE".equals(modeType)) {
            try {
                sharedPref = context.getSharedPreferences(prefFile, Context.MODE_APPEND);
            } catch (Exception e) {
                callbackContext.error("Error creating Shared Preferences" + e.getMessage());
                return false;
            }
            callbackContext.success("Shared Preferences Created");
            return true;
        } else {
            callbackContext.error("Invalid Mode provided");
            return false;
        }
    }

    private boolean putStringPref(JSONArray args, CallbackContext callbackContext) throws JSONException {
        Editor editor = sharedPref.edit();
        try {
            editor.putString(args.getString(0), args.getString(1));
            editor.commit();
        } catch (Exception e) {
            callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getString(1) + e.getMessage());
            return false;
        }
        callbackContext.success("Added Value " + args.getString(1) + " to Preferences key " + args.getString(0));
        return true;
    }

    private boolean getStringPref(JSONArray args, CallbackContext callbackContext) throws JSONException {
        String keyVal;
        try {
            if (sharedPref.contains(args.getString(0))) {
                keyVal = sharedPref.getString(args.getString(0), "");
                callbackContext.success(keyVal);
                return true;
            } else {
                callbackContext.error("No data");
                return false;
            }
        } catch (Exception e) {
            callbackContext.error("Could Not Retreive " + args.getString(0) + e.getMessage());
            return false;
        }
    }

    private boolean removePref(JSONArray args, CallbackContext callbackContext) throws JSONException {
        Editor editor = sharedPref.edit();
        try {
            editor.remove(args.getString(0));
            editor.commit();
        } catch (Exception e) {
            callbackContext.error("Error editing Key " + args.getString(0) + " with value " + args.getLong(1) + e.getMessage());
            return false;
        }
        callbackContext.success("Removed Value from Key " + args.getString(0));
        return true;
    }

    private boolean clearPrefs(CallbackContext callbackContext) {
        Editor editor = sharedPref.edit();
        try {
            editor.clear();
            editor.commit();
        } catch (Exception e) {
            callbackContext.error("Could Not Clear Shared preference File " + e.getMessage());
            return false;
        }
        callbackContext.success("Cleared preference File ");
        return true;
    }
}
