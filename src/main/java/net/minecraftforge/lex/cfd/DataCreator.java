/*
 * Copyright (c) LexManos
 * SPDX-License-Identifier: LGPL-2.1-only
 */
package net.minecraftforge.lex.cfd;

public class DataCreator {
    /*@SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        var gen = event.getGenerator();
        var out = gen.getPackOutput();
        var regs = event.getLookupProvider();
        var helper = event.getExistingFileHelper();

        gen.addProvider(event.includeServer(), new Recipes(out, regs));
        gen.addProvider(event.includeServer(), new Loots(out, regs));
        gen.addProvider(event.includeServer(), new TagsProvider(out, regs, helper));

        gen.addProvider(event.includeClient(), new Language(out));
        gen.addProvider(event.includeClient(), new BlockStates(out, helper));
        gen.addProvider(event.includeClient(), new ItemModels(out, helper));
    }

    private static class Recipes extends RecipeProvider {
        public Recipes(PackOutput out, CompletableFuture<HolderLookup.Provider> registries) {
            super(out, registries);
        }

        @Override
        protected void buildRecipes(RecipeOutput output) {
            getTier(TIER1_BLOCK.get(), ItemTags.LOGS).save(output);
            getTier(TIER2_BLOCK.get(), Tags.Items.COBBLESTONE).save(output);
            getTier(TIER3_BLOCK.get(), Tags.Items.INGOTS_IRON).save(output);
            getTier(TIER4_BLOCK.get(), Tags.Items.INGOTS_GOLD).save(output);
            getTier(TIER5_BLOCK.get(), Tags.Items.GEMS_DIAMOND).save(output);
        }

        private ShapedRecipeBuilder getTier(ItemLike item, TagKey<Item> resource) {
            return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, item)
                .define('W', Items.WATER_BUCKET)
                .define('L', Items.LAVA_BUCKET)
                .define('G', Blocks.GLASS)
                .define('R', resource)
                .pattern("RRR")
                .pattern("WGL")
                .pattern("RRR")
                .unlockedBy("has_lava", has(Items.LAVA_BUCKET))
                .unlockedBy("has_water", has(Items.WATER_BUCKET));
        }
    }

    private static class Loots extends LootTableProvider {
        public Loots(PackOutput out, CompletableFuture<HolderLookup.Provider> registries) {
            super(out, Set.of(), List.of(new SubProviderEntry(Blocks::new, LootContextParamSets.BLOCK)), registries);
        }

        @Override
        protected void validate(net.minecraft.core.Registry<LootTable> map, ValidationContext ctxt, ProblemReporter report) {
           map.holders().forEach(table -> table.value().validate(
               ctxt.setParams(table.value().getParamSet())
                   .enterElement("{" + table.key().location() + "}", table.key())
           ));
        }

        private static class Blocks extends BlockLootSubProvider {
            protected Blocks(HolderLookup.Provider registries) {
                super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
            }

            @Override
            protected void generate() {
                this.dropSelf(TIER1_BLOCK.get());
                this.dropSelf(TIER2_BLOCK.get());
                this.dropSelf(TIER3_BLOCK.get());
                this.dropSelf(TIER4_BLOCK.get());
                this.dropSelf(TIER5_BLOCK.get());
            }

            @Override
            protected Iterable<Block> getKnownBlocks() {
                return BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
            }
        }
    }

    private static class Language extends LanguageProvider {
        public Language(PackOutput out) {
            super(out, MODID, "en_us");
        }

        @Override
        protected void addTranslations() {
            add(TIER1_BLOCK.get(), "Cobble Gen Tier1");
            add(TIER2_BLOCK.get(), "Cobble Gen Tier2");
            add(TIER3_BLOCK.get(), "Cobble Gen Tier3");
            add(TIER4_BLOCK.get(), "Cobble Gen Tier4");
            add(TIER5_BLOCK.get(), "Cobble Gen Tier5");
        }
    }

    private static class ItemModels extends ItemModelProvider {
        public ItemModels(PackOutput out, ExistingFileHelper helper) {
            super(out, MODID, helper);
        }

        @Override
        protected void registerModels() {
            makeTier(TIER1_BLOCK);
            makeTier(TIER2_BLOCK);
            makeTier(TIER3_BLOCK);
            makeTier(TIER4_BLOCK);
            makeTier(TIER5_BLOCK);
        }

        private void makeTier(RegistryObject<Block> block) {
            String path = block.getKey().location().getPath();
            getBuilder(path)
            .parent(new ModelFile.UncheckedModelFile(modLoc("block/" + path)));
        }

        @Override
        public String getName() {
            return "Item Models";
        }
    }

    private static class BlockStates extends BlockStateProvider {

        public BlockStates(PackOutput out, ExistingFileHelper helper) {
            super(out, MODID, helper);
        }

        @Override
        protected void registerStatesAndModels() {
            makeTier(TIER1_BLOCK, mcLoc("block/acacia_log"));
            makeTier(TIER2_BLOCK, mcLoc("block/cobblestone"));
            makeTier(TIER3_BLOCK, mcLoc("block/iron_block"));
            makeTier(TIER4_BLOCK, mcLoc("block/gold_block"));
            makeTier(TIER5_BLOCK, mcLoc("block/diamond_block"));
        }

        private void makeTier(RegistryObject<Block> block, ResourceLocation texture) {
            ModelFile model = models().getBuilder(block.getKey().location().getPath())
                .parent(models().getExistingFile(modLoc("block/generator")))
                .texture("material", texture);
            getVariantBuilder(block.get()).forAllStates(state -> ConfiguredModel.builder().modelFile(model).build());
        }
    }

    private static class TagsProvider extends BlockTagsProvider {
        public TagsProvider(PackOutput output, CompletableFuture<Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
            super(output, lookupProvider, MODID, existingFileHelper);
        }

        @Override
        protected void addTags(Provider provider) {
            this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                TIER1_BLOCK.get(),
                TIER2_BLOCK.get(),
                TIER3_BLOCK.get(),
                TIER4_BLOCK.get(),
                TIER5_BLOCK.get()
            );
        }
    }*/
}
