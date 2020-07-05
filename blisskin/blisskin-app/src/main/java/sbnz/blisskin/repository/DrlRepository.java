package sbnz.blisskin.repository;

import org.springframework.stereotype.Repository;
import sbnz.blisskin.exceptions.BadRequestException;

import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Repository
public class DrlRepository {

    private static final String DRL_RULES_PATH = "..\\blisskin-kjar\\src\\main\\resources\\rules\\generated";
    private static final String DRL_EXTENSION = ".drl";

    public void save(String name, String rule) {
        final Path rulePath = this.formatRulePath(name);
        try (final Writer writer = Files.newBufferedWriter(rulePath)) {
            writer.write(rule);
        } catch (Exception e) {
            throw new BadRequestException("Could not save rule");
        }
    }

    private Path formatRulePath(String name) {
        return Paths.get(DRL_RULES_PATH, name + DRL_EXTENSION);
    }
}
