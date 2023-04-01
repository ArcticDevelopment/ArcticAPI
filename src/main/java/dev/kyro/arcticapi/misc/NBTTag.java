package dev.kyro.arcticapi.misc;

public enum NBTTag {
	ITEM_TAG("aapi-itemtag"),
	;

	private final String ref;

	NBTTag(String ref) {
		this.ref = ref;
	}

	public String getRef() {
		return ref;
	}
}
