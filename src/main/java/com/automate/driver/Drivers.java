package com.automate.driver;

import com.automate.constants.FrameworkConstants;
import com.automate.customexceptions.DriverInitializationException;
import com.automate.enums.ConfigJson;
import com.automate.enums.MobileBrowserName;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.MobileCapabilityType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

import static com.automate.utils.configloader.JsonUtils.getConfig;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Drivers {

  public static AppiumDriver<MobileElement> createAndroidDriverForNativeApp(String deviceName, String udid, int port, String emulator) {
    try {
      var capability = new DesiredCapabilities();
      capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
      capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2); // Specific to Android
      capability.setCapability(MobileCapabilityType.UDID, udid); // To uniquely identify device
      capability.setCapability(MobileCapabilityType.APP, FrameworkConstants.ANDROID_APK_PATH);
      capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, getConfig(ConfigJson.APP_PACKAGE));
      capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, getConfig(ConfigJson.APP_ACTIVITY));
      capability.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT,
                               port); // To set different port for each thread - This port is used to communicate with UiAutomator2
      if (emulator.equalsIgnoreCase("yes")) {
        capability.setCapability(AndroidMobileCapabilityType.AVD, deviceName);
        capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT,
                                 Integer.parseInt(getConfig(ConfigJson.AVD_LAUNCH_TIMEOUT)));
      }
      return new AndroidDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }

  public static AppiumDriver<MobileElement> createAndroidDriverForWeb(String deviceName, String udid, int port, String emulator) {
    try {
      var capability = new DesiredCapabilities();
      capability.setCapability(CapabilityType.PLATFORM_NAME, Platform.ANDROID);
      capability.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
      capability.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.ANDROID_UIAUTOMATOR2);
      capability.setCapability(MobileCapabilityType.UDID, udid);
      capability.setCapability(CapabilityType.BROWSER_NAME, MobileBrowserName.CHROME);
      capability.setCapability(AndroidMobileCapabilityType.CHROMEDRIVER_PORT,
                               port); // For Web view/Chrome browser to launch the browser on different port
      if (emulator.equalsIgnoreCase("yes")) {
        capability.setCapability(AndroidMobileCapabilityType.AVD, deviceName);
        capability.setCapability(AndroidMobileCapabilityType.AVD_LAUNCH_TIMEOUT,
                                 Integer.parseInt(getConfig(ConfigJson.AVD_LAUNCH_TIMEOUT)));
      }

      return new AndroidDriver<>(new URL(getConfig(ConfigJson.APPIUM_URL)), capability);
    } catch (Exception e) {
      throw new DriverInitializationException("Failed to initialize driver. Please check the desired capabilities", e);
    }
  }




}
