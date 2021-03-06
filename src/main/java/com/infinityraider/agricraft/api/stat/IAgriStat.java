package com.infinityraider.agricraft.api.stat;

import java.util.List;
import javax.annotation.Nonnull;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Interface for representing stats. Stats are immutable objects, to aid in
 * overall safety.
 *
 * 
 */
public interface IAgriStat {

	/**
	 * Retrieves the stat's serialization ID.
	 *
	 * @return the stat's id.
	 */
	default String getId() {
		return this.getClass().getCanonicalName();
	}

	/**
	 * @return if the seed stats are analyzed
	 */
	boolean isAnalyzed();

	/**
	 * The meta value for the object. Placed here so that seeds may store an
	 * initial growth amount.
	 *
	 * @return
	 */
	byte getMeta();

	/**
	 * @return The growth value of the seed.
	 */
	byte getGrowth();

	/**
	 * @return The gain value of the seed.
	 */
	byte getGain();

	/**
	 * @return The strength value of the seed.
	 */
	byte getStrength();

	/**
	 * @return The maximum growth value a seed of this kind can have.
	 */
	byte getMaxGrowth();

	/**
	 * @return The maximum gain value a seed of this kind can have.
	 */
	byte getMaxGain();

	/**
	 * @return The maximum strength value a seed of this kind can have.
	 */
	byte getMaxStrength();

	/**
	 * Sets the analyzed parameter.
	 *
	 * @param analyzed
	 * @return the new stat.
	 */
	IAgriStat withAnalyzed(boolean analyzed);

	/**
	 * Sets the meta value.
	 *
	 * @param meta the meta value.
	 * @return the new stat.
	 */
	IAgriStat withMeta(int meta);

	/**
	 * Sets the growth stat.
	 *
	 * @param growth
	 * @return the new stat.
	 */
	IAgriStat withGrowth(int growth);

	/**
	 * Sets the gain stat.
	 *
	 * @param gain
	 * @return the new stat.
	 */
	IAgriStat withGain(int gain);

	/**
	 * Sets the strength stat.
	 *
	 * @param strength
	 * @return the new stat.
	 */
	IAgriStat withStrength(int strength);

	/**
	 * Writes the stat to an NBTTagcompound.
	 * Make sure to set the stat id value when implementing this.
	 *
	 * @param tag The tag to serialize to.
	 * @return if the transcription was successful.
	 */
	boolean writeToNBT(@Nonnull NBTTagCompound tag);

	/**
	 * Writes the stat for display.
	 *
	 * @param lines The line list to add to.
	 * @return If the writing was successful.
	 */
	boolean addStats(@Nonnull List<String> lines);

}
