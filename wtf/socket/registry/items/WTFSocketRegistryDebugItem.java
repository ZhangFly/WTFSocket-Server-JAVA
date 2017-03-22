package wtf.socket.registry.items;

import io.netty.channel.Channel;
import wtf.socket.protocols.templates.WTFSocketConnectType;

import java.util.ArrayList;
import java.util.List;

/**
 * 调试账号注册表条目
 */
public class WTFSocketRegistryDebugItem extends WTFSocketRegistryUserItem {

    private List<String> filterGreps = new ArrayList<>();
    private boolean isShowHeartbeatMsg = false;

    public WTFSocketRegistryDebugItem(final String name, final Channel channel, final WTFSocketConnectType connectType, final String accept) {
        super(name, channel, connectType, accept, "Debug");
    }

    public void addFilterGrep(final String grep) {
        filterGreps.add(grep);
    }

    public void removeFilterGrep(String grep) {
        if (filterGreps.contains(grep)) {
            filterGreps.remove(grep);
        }
    }

    public boolean isFilter(String msg) {
        if (filterGreps.isEmpty()) {
            return true;
        }
        boolean flag = false;
        for (String grep : filterGreps) {
            flag = flag || msg.contains("<" + grep + ">");
        }
        return flag;
    }

    public void clearFilterGreps() {
        filterGreps.clear();
    }

    public boolean isShowHeartbeatMsg() {
        return isShowHeartbeatMsg;
    }

    public void setShowHeartbeatMsg(boolean showHeartbeatMsg) {
        isShowHeartbeatMsg = showHeartbeatMsg;
    }
}
