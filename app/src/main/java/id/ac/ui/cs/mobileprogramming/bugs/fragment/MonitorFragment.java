package id.ac.ui.cs.mobileprogramming.bugs.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import id.ac.ui.cs.mobileprogramming.bugs.R;
import id.ac.ui.cs.mobileprogramming.bugs.core.NetworkData;

public class MonitorFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View root = inflater.inflate(R.layout.fragment_monitor, container, false);
        fetchData(root);
        return root;
    }

    private void fetchData(View root) {
        String macAddress = NetworkData.getMacAddress(root.getContext());
        ((TextView) root.findViewById(R.id.network_mac_address)).setText(macAddress);

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
