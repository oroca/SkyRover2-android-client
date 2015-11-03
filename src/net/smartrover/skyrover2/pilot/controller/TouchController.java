/**
 *
 * SkyRover2 Multi-Quadcopter Client
 *
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 * This file has been created with reference to the
 *  > https://github.com/bitcraze/crazyflie-android-client/blob/master/src/se/bitcraze/crazyfliecontrol/controller/TouchController.java
 */

package net.smartrover.skyrover2.pilot.controller;

import net.smartrover.skyrover2.pilot.MainActivity;
import net.smartrover.skyrover2.pilot.R;

import com.MobileAnarchy.Android.Widgets.Joystick.DualJoystickView;
import com.MobileAnarchy.Android.Widgets.Joystick.JoystickMovedListener;
import com.MobileAnarchy.Android.Widgets.Joystick.JoystickView;

/**
 * The TouchController uses the on-screen joysticks to control the roll, pitch, yaw and thrust values.
 * The mapping of the axes can be changed with the "mode" setting in the preferences.
 *
 * For example, mode 3 (default) maps roll to the left X-Axis, pitch to the left Y-Axis,
 * yaw to the right X-Axis and thrust to the right Y-Axis.
 *
 * @author OROCA SmartRover Team
 */
public class TouchController extends AbstractController {

    protected int mMovementRange = 1000;  // "resolution"

    protected DualJoystickView mDualJoystickView;

    public TouchController(Controls controls, MainActivity activity, DualJoystickView dualJoystickview) {
        super(controls, activity);
        this.mDualJoystickView = dualJoystickview;
        this.mDualJoystickView.setMovementRange(mMovementRange, mMovementRange);
        updateAutoReturnMode();
    }

    private void updateAutoReturnMode() {
        this.mDualJoystickView.setAutoReturnMode(
                isLeftAnalogFullTravelThrust() ? JoystickView.AUTO_RETURN_BOTTOM : JoystickView.AUTO_RETURN_CENTER,
                isRightAnalogFullTravelThrust() ? JoystickView.AUTO_RETURN_BOTTOM : JoystickView.AUTO_RETURN_CENTER
        );
        this.mDualJoystickView.autoReturn(true);
    }

    @Override
    public void enable() {
        super.enable();
        this.mDualJoystickView.setOnJoystickMovedListener(_listenerLeft, _listenerRight);
        updateAutoReturnMode();
    }

    @Override
    public void disable() {
        mControls.setRightAnalogY(0);
        mControls.setRightAnalogX(0);
        mControls.setLeftAnalogY(0);
        mControls.setLeftAnalogX(0);
        this.mDualJoystickView.setOnJoystickMovedListener(null, null);
        super.disable();
    }

    public String getControllerName() {
        return mActivity.getResources().getString(R.string.name_touch_controller);
    }

    private JoystickMovedListener _listenerRight = new JoystickMovedListener() {

        @Override
        public void OnMoved(float pan, float tilt) {
            if (isRightAnalogFullTravelThrust()) {
                tilt = (tilt + 1.0f) / 2.0f;
            }
            mControls.setRightAnalogY(tilt);

            mControls.setRightAnalogX(pan);

            updateFlightData();
        }

        @Override
        public void OnReleased() {
            // Log.i("Joystick-Right", "Release");
            mControls.setRightAnalogY(0);
            mControls.setRightAnalogX(0);
        }

        public void OnReturnedToCenter() {
            // Log.i("Joystick-Right", "Center");
            mControls.setRightAnalogY(0);
            mControls.setRightAnalogX(0);
        }
    };

    private JoystickMovedListener _listenerLeft = new JoystickMovedListener() {

        @Override
        public void OnMoved(float pan, float tilt) {
            if (isLeftAnalogFullTravelThrust()) {
                tilt = (tilt + 1.0f) / 2.0f;
            }
            mControls.setLeftAnalogY(tilt);

            mControls.setLeftAnalogX(pan);

            updateFlightData();
        }

        @Override
        public void OnReleased() {
            mControls.setLeftAnalogY(0);
            mControls.setLeftAnalogX(0);
        }

        public void OnReturnedToCenter() {
            mControls.setLeftAnalogY(0);
            mControls.setLeftAnalogX(0);
        }
    };


    public boolean isThrustRightAnalog() {
        return (mControls.getMode() == 1 || mControls.getMode() == 3);
    }

    public boolean isLeftAnalogFullTravelThrust() {
        return mControls.isTouchThrustFullTravel() && !isThrustRightAnalog();
    }

    public boolean isRightAnalogFullTravelThrust() {
        return mControls.isTouchThrustFullTravel() && isThrustRightAnalog();
    }

}
