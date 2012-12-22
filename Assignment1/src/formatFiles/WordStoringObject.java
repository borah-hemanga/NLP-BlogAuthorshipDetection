package formatFiles;

public class WordStoringObject{
	String word;
	int count;
	int docNo;
	int appearedIn;
	float idf;
	
	public WordStoringObject(String word,int count,int docNo,int appearedIn) {
		this.word = word;
		this.count = count;
		this.docNo = docNo;
		this.appearedIn = appearedIn;
	}
	
	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getDocNo() {
		return docNo;
	}

	public void setDocNo(int docNo) {
		this.docNo = docNo;
	}

	public int getAppearedIn() {
		return appearedIn;
	}

	public void setAppearedIn(int appearedIn) {
		this.appearedIn = appearedIn;
	}

	public float getIdf() {
		return idf;
	}

	public void setIdf(float idf) {
		this.idf = idf;
	}

	@Override
	public int hashCode() {
		return word.hashCode();
	}
}