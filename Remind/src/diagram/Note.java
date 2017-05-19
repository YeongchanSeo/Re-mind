package diagram;

public class Note {

	private String what;
	private String why;
	private String how;

	public Note() {
		this("","","");
	}

	public Note(String what, String why, String how) {
		this.what = what;
		this.why = why;
		this.how = how;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((how == null) ? 0 : how.hashCode());
		result = prime * result + ((what == null) ? 0 : what.hashCode());
		result = prime * result + ((why == null) ? 0 : why.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Note other = (Note) obj;
		if (how == null) {
			if (other.how != null)
				return false;
		} else if (!how.equals(other.how))
			return false;
		if (what == null) {
			if (other.what != null)
				return false;
		} else if (!what.equals(other.what))
			return false;
		if (why == null) {
			if (other.why != null)
				return false;
		} else if (!why.equals(other.why))
			return false;
		return true;
	}

	public String getWhat() {
		return what;
	}

	public void setWhat(String what) {
		this.what = what;
	}

	public String getWhy() {
		return why;
	}

	public void setWhy(String why) {
		this.why = why;
	}

	public String getHow() {
		return how;
	}

	public void setHow(String how) {
		this.how = how;
	}

	@Override
	public String toString() {
		return "Note [what=" + what + ", why=" + why + ", how=" + how + "]";
	}

}
