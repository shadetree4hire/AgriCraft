package com.infinityraider.agricraft.network.json;

import com.agricraft.agricore.core.AgriCore;
import com.agricraft.agricore.json.AgriSaver;
import com.agricraft.agricore.plant.AgriSoil;
import com.google.gson.Gson;
import com.infinityraider.agricraft.apiimpl.SoilRegistry;
import com.infinityraider.agricraft.core.CoreHandler;
import com.infinityraider.agricraft.core.JsonSoil;
import java.nio.file.Path;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncSoilJson extends MessageSyncElement<AgriSoil> {

	private static final Gson gson = new Gson();

	@SuppressWarnings("unused")
	public MessageSyncSoilJson() {}
	
	public MessageSyncSoilJson(AgriSoil soil, int index, int count) {
		super(soil, index, count);
	}

	@Override
	protected String toString(AgriSoil element) {
		return gson.toJson(element);
	}

	@Override
	protected AgriSoil fromString(String element) {
		return gson.fromJson(element, AgriSoil.class);
	}
	
	@Override
	public void onSyncStart(MessageContext ctx) {
		AgriCore.getPlants().clearElements();
	}

	@Override
	protected void onMessage(MessageContext ctx) {
		AgriCore.getSoils().addSoil(this.element);
	}
	
	@Override
	public void onFinishSync(MessageContext ctx) {
		final Path worldDir = CoreHandler.getJsonDir().resolve(this.getServerId());
		AgriSaver.saveElements(worldDir, AgriCore.getSoils().getAll());
		AgriCore.getSoils().getAll().stream()
				.map(JsonSoil::new)
				.forEach(SoilRegistry.getInstance()::addSoil);
	}

}
