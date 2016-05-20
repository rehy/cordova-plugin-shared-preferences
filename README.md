Cordova Plugin for Android SharedPreferences
============================================

## Installation

```bash
cordova plugin add cordova-plugin-shared-preferences --save
```

## Usage

```javascript
document.addEventListener('deviceready', () => {
  const prefs = window.plugins.SharedPreferences
  prefs.getSharedPreferences('shared_preferences', 'MODE_PRIVATE', () => {
    prefs.putString('pref_key', 'some text')

    prefs.getString('pref_key', (value) => {
      alert(value)
    }, (error) => {
      // handle error
    })
  }, (error) => {
    // handle error
  })
}
```
