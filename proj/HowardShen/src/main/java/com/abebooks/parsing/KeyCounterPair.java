package com.abebooks.parsing;

/**
 *  key-counter pair
 */
public class KeyCounterPair implements Comparable<KeyCounterPair> {

	private String key;

	private Integer count;

	public KeyCounterPair(String key, Integer count) {
		this.key = key;
		this.count = count;
	}

	public String getKey() {
		return key;
	}

	public Integer getCount() {
		return count;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((count == null) ? 0 : count.hashCode());
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof KeyCounterPair)) {
			return false;
		}
		KeyCounterPair other = (KeyCounterPair) obj;
		if (count == null) {
			if (other.count != null) {
				return false;
			}
		} else if (!count.equals(other.count)) {
			return false;
		}
		if (key == null) {
			if (other.key != null) {
				return false;
			}
		} else if (!key.equals(other.key)) {
			return false;
		}
		return true;
	}

	@Override
	public int compareTo(KeyCounterPair o) {
		Integer otherValue = o.getCount();
		return this.count.compareTo(otherValue);
	}

	@Override
	public String toString() {
		return "KeyTerm [count=" + count + ", key=" + key + "]";
	}

}