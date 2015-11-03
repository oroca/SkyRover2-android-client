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
 *  > https://github.com/bitcraze/crazyflie-android-client/blob/master/src/se/bitcraze/crazyfliecontrol/prefs/SelectConnectionDialogFragment.java
 */

package net.smartrover.skyrover2.pilot.prefs;

import net.smartrover.skyrover2.pilot.R;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

/**
 * @author OROCA SmartRover Team
 */
public class SelectConnectionDialogFragment extends DialogFragment {

    public interface SelectSkyRoverDialogListener {
        public void onClick(int which);
    }

    SelectSkyRoverDialogListener mListener;

    public void setListener(SelectSkyRoverDialogListener listener){
        this.mListener = listener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String[] skyroverList = getArguments().getStringArray("connection_array");

        builder.setTitle(R.string.select_connection_dialog_title);
        builder.setItems(skyroverList, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    if(mListener != null){
                        mListener.onClick(which);
                    }
                }
            });

        return builder.create();
    }
}