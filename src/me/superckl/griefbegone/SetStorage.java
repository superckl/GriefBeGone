package me.superckl.griefbegone;

import java.util.Set;

public class SetStorage<K> {

	private Set<K> stored;

	public void store(final Set<K> set){
		this.stored = set;
	}

	public Set<K> getStored(){
		return this.stored;
	}

}
