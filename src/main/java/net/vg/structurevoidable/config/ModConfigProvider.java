package net.vg.structurevoidable.config;

import com.mojang.datafixers.util.Pair;
import net.vg.structurevoidable.StructureVoidable;

import java.util.ArrayList;
import java.util.List;

public class ModConfigProvider implements SimpleConfig.DefaultConfig {

    private final List<Pair<String, List<String>>> comments = new ArrayList<>();
    private final List<Pair<String, ?>> configsList = new ArrayList<>();

    public List<Pair<String, List<String>>> getConfigsList() {
        return comments;
    }

    public void addKeyValuePair(Pair<String, ?> keyValuePair, List<String> comment) {
        configsList.add(keyValuePair);
        comments.add(new Pair<>(keyValuePair.getFirst(), comment));
    }

    @Override
    public String get(String namespace) {
        StringBuilder sb = new StringBuilder();
        sb.append("# " + StructureVoidable.MOD_NAME + " Settings\n\n");
        for (int i = 0; i < configsList.size(); i++) {
            Pair<String, ?> config = configsList.get(i);
            List<String> comment = comments.get(i).getSecond();
            for (String line : comment) {
                sb.append("# ").append(line).append("\n");
            }
            sb.append(config.getFirst()).append("=").append(config.getSecond()).append("\n\n");
        }
        return sb.toString();
    }
}
