package uhcrun.game;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapGenerator extends ChunkGenerator {

    private final UHCRun plugin;

    public MapGenerator(UHCRun plugin) {
        this.plugin = plugin;
    }

    @Override
    public ChunkData generateChunkData(World world, Random random, int x, int z, BiomeGrid biome) {
        ChunkData chunkData = createChunkData(world);
        int y = 40;

        // Fill the bottom half of the chunk with stone
        for (int cx = 0; cx < 16; cx++) {
            for (int cz = 0; cz < 16; cz++) {
                for (int cy = 0; cy < y; cy++) {
                    chunkData.setBlock(cx, cy, cz, Material.STONE);
                }
            }
        }

        // Generate ores
        generateOres(chunkData, random);

        // Place bedrock at the bottom
        for (int cx = 0; cx < 16; cx++) {
            for (int cz = 0; cz < 16; cz++) {
                chunkData.setBlock(cx, 0, cz, Material.BEDROCK);
            }
        }

        return chunkData;
    }

    private void generateOres(ChunkData chunkData, Random random) {
        // Generate coal ore
        generateOre(chunkData, Material.COAL_ORE, random, 20, 16, 128);

        // Generate iron ore
        generateOre(chunkData, Material.IRON_ORE, random, 20, 0, 64);

        // Generate gold ore
        generateOre(chunkData, Material.GOLD_ORE, random, 2, 0, 32);

        // Generate diamond ore
        generateOre(chunkData, Material.DIAMOND_ORE, random, 1, 0, 16);

        // Generate redstone ore
        generateOre(chunkData, Material.REDSTONE_ORE, random, 8, 0, 16);

        // Generate lapis ore
        generateOre(chunkData, Material.LAPIS_ORE, random, 1, 0, 32);
    }

    private void generateOre(ChunkData chunkData, Material ore, Random random, int veinSize, int minHeight, int maxHeight) {
        for (int i = 0; i < veinSize; i++) {
            int x = random.nextInt(16);
            int y = random.nextInt(maxHeight - minHeight) + minHeight;
            int z = random.nextInt(16);
            Location loc = new Location(null, x, y, z);
            chunkData.setBlock(x, y, z, ore);
        }
    }

    @Override
    public List<BlockPopulator> getDefaultPopulators(World world) {
        return new ArrayList<>();
    }

    @Override
    public Location getFixedSpawnLocation(World world, Random random) {
        return new Location(world, 0, 80, 0);
    }
}