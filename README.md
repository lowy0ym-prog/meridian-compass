# Meridian Compass

A simple, clean, privacy-focused Android waypoint/navigation app for marking and finding locations.

## Features

- **Interactive Map**: Real-time map with dark theme, zooming, and panning
- **GPS Location**: Show your current position and mark it
- **Mark on Map**: Select any point on the map to save as a waypoint
- **Search**: Find locations by address or coordinates
- **Record Trail**: Track your GPS path and save it
- **Waypoints**: Save, organize, and manage your favorite locations
- **Distance Calculation**: See distance between you and waypoints
- **Privacy-First**: No ads, no tracking, no login required
- **Offline-Ready**: Works without internet (with cached data)

## Building the APK

The easiest way to build this app is using GitHub Actions:

1. Push code to this repository
2. Go to **Actions** tab on GitHub
3. Select the **Build Meridian Compass Debug APK** workflow
4. Click **Run workflow**
5. Wait for the build to complete
6. Download the APK from **Artifacts**

### Manual Build (if needed)

Requirements:
- Java 17+
- Android SDK (API 34)
- Gradle

```bash
./gradlew assembleDebug
```

The APK will be in: `app/build/outputs/apk/debug/`

## Map Configuration

The app uses MapLibre with OpenStreetMap tiles. To change the map provider:

1. Edit `app/src/main/java/com/meridian/compass/map/MapConfig.kt`
2. Change the `TILE_URL` and provider settings

Default tile provider: OpenStreetMap (free, no API key required)

## Permissions

The app requests only necessary permissions:
- **Location**: For GPS and waypoints
- **Internet**: For map tiles and search
- **Notifications**: For trail recording status

## Privacy

- No analytics or tracking
- No social features
- No unnecessary permissions
- All data stored locally
- No account required

## Android Requirements

- Minimum SDK: 26 (Android 8.0)
- Target SDK: 34 (Android 14)
- Kotlin 1.9.20
- Jetpack Compose UI

## Project Structure

```
app/src/main/
├── java/com/meridian/compass/
│   ├── MainActivity.kt
│   ├── ui/
│   │   ├── screens/          # UI screens
│   │   └── theme/            # Theme and colors
│   ├── location/             # GPS and location services
│   ├── data/
│   │   ├── db/               # Room database
│   │   └── models/           # Data models
│   └── map/                  # Map configuration
├── res/
│   ├── values/               # Resources
│   └── xml/                  # Backup rules
└── AndroidManifest.xml
```

## License

MIT License - Feel free to use and modify!

## Support

For issues or questions, open an issue on GitHub.
