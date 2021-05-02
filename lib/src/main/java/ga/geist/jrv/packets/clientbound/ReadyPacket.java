package ga.geist.jrv.packets.clientbound;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import ga.geist.jrv.RevoltBridge;
import ga.geist.jrv.SocketConnector;
import ga.geist.jrv.packets.ClientboundPacket;
import ga.geist.jrv.types.Channel;
import ga.geist.jrv.types.DirectMessage;
import ga.geist.jrv.types.GroupDM;
import ga.geist.jrv.types.User;

public class ReadyPacket implements ClientboundPacket {
    private void addUsers(JSONObject message, RevoltBridge bridge) {
        JSONArray users = message.getJSONArray("users");

        List<User> userList = new ArrayList<>();

        for (int i = 0; i < users.length(); i++) {
            userList.add(User.fromJSON(users.getJSONObject(i)));
        }

        for (User user : userList) {
            bridge.getRegistries().getUserRegistry().add(user.getId(), user);
        }
    }

    private void addChannels(JSONObject message, RevoltBridge bridge) {
        JSONArray channels = message.getJSONArray("channels");

        List<Channel> channelList = new ArrayList<>();
        List<GroupDM> gdmList = new ArrayList<>();
        List<DirectMessage> dmList = new ArrayList<>();

        for (int i = 0; i < channels.length(); i++) {
            JSONObject channel = channels.getJSONObject(i);

            if (channel.optString("channel_type").equals("Group"))
                gdmList.add(GroupDM.fromJSON(channel));
            else if (channel.optString("channel_type").equals("DirectMessage"))
                dmList.add(DirectMessage.fromJSON(channel));

            channelList.add(new Channel(channel.optString("channel_type"), channel.optString("_id")));
        }

        for (Channel channel : channelList) {
            bridge.getRegistries().getChannelRegistry().add(channel.getId(), channel);
        }
        for (GroupDM gdm : gdmList) {
            bridge.getRegistries().getGdmRegistry().add(gdm.getId(), gdm);
        }
        for (DirectMessage dm : dmList) {
            bridge.getRegistries().getDmRegistry().add(dm.getId(), dm);
        }
    }

    @Override
    public void pass(String stringMessage, SocketConnector client) {
        JSONObject message = new JSONObject(stringMessage);

        addUsers(message, client.getBridge());
        addChannels(message, client.getBridge());
    }
}
