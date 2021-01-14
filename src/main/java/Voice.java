import net.dv8tion.jda.api.entities.VoiceChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.managers.AudioManager;

public class Voice {
    public void Connect(GuildMessageReceivedEvent event){
        VoiceChannel channel = event.getGuild().getVoiceChannelsByName("General", true).get(0);
        AudioManager audioManager = event.getGuild().getAudioManager();
        audioManager.openAudioConnection(channel);
    }
}
