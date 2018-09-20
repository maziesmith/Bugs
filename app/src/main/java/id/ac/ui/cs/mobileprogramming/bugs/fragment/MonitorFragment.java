package id.ac.ui.cs.mobileprogramming.bugs.fragment;

import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.bugs.R;
import id.ac.ui.cs.mobileprogramming.bugs.core.NetworkData;

public class MonitorFragment extends Fragment {
    public static final int PHONE_PERMISSION = 0;
    private View root;

    public class BatteryLevelReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
//            int scale = intent.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
//            float batteryPct = level / (float) scale;

            TextView data = root.findViewById(R.id.phone_battery_level);
//            Toast.makeText(context, "Level : " + level + "\nScale : " + scale + "\nPercentage : " + batteryPct, Toast.LENGTH_SHORT).show();
            data.setText(level + "%");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        root = inflater.inflate(R.layout.fragment_monitor, container, false);

        IntentFilter intentFilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        BatteryLevelReceiver receiver = new BatteryLevelReceiver();
        getContext().registerReceiver(receiver, intentFilter);

        fetchData(root);
        return root;
    }

    private void fetchData(View root) {
        String macAddress = NetworkData.getMacAddress(root.getContext());
        ((TextView) root.findViewById(R.id.network_mac_address)).setText(macAddress);

        String providerName = NetworkData.getProviderName(root.getContext());
        ((TextView) root.findViewById(R.id.phone_provider_name)).setText(providerName);

        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_PHONE_STATE}, PHONE_PERMISSION);

        String networkId = Integer.toString(NetworkData.getNetworkId(root.getContext()));
        ((TextView) root.findViewById(R.id.network_network_id)).setText(networkId);

        String networkSSID = NetworkData.getSSID(root.getContext());
        ((TextView) root.findViewById(R.id.network_ssid)).setText(networkSSID);

        String networkBSSID = NetworkData.getBSSID(root.getContext());
        ((TextView) root.findViewById(R.id.network_bssid)).setText(networkBSSID);

        String ipAddress = NetworkData.getIPAddress(root.getContext());
        ((TextView) root.findViewById(R.id.network_ip_address)).setText(ipAddress);

        String linkSpeed = Integer.toString(NetworkData.getLinkSpeed(root.getContext()));
        ((TextView) root.findViewById(R.id.network_link_speed)).setText(linkSpeed);

        String frequency = Integer.toString(NetworkData.getFrequency(root.getContext()));
        ((TextView) root.findViewById(R.id.network_frequency)).setText(frequency);
    }
}
