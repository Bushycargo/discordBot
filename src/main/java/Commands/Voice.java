package Commands;

import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Voice {
    public void Connect(GuildMessageReceivedEvent event, String ChannelName){
        VoiceChannel channel = event.getGuild().getVoiceChannelsByName(ChannelName, true).get(0);
        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.openAudioConnection(channel);
    }
}
