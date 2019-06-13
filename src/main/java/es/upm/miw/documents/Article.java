package es.upm.miw.documents;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Article {

	@Id
	private String code;

	private LocalDateTime registrationDate;

	private String description;

	private BigDecimal retailPrice;

	private String reference;

	private Integer stock;

	private Tax tax;

	private Boolean discontinued;

	private String providerId;

	public Article() {
		this.registrationDate = LocalDateTime.now();
	}

	public static Builder builder(String code) {
		return new Builder(code);
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Boolean getDiscontinued() {
		return discontinued;
	}

	public void setDiscontinued(Boolean discontinued) {
		this.discontinued = discontinued;
	}

	public String getProviderId() {
		return providerId;
	}

	public void setProviderId(String providerId) {
		this.providerId = providerId;
	}

	public Tax getTax() {
		return tax;
	}

	public void setTax(Tax tax) {
		this.tax = tax;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	@Override
	public int hashCode() {
		return this.code.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		return this == obj || obj != null && getClass() == obj.getClass() && (code.equals(((Article) obj).code));
	}

	@Override
	public String toString() {
		return "Article{" + "code='" + code + '\'' + ", registrationDate=" + registrationDate + ", description='"
				+ description + '\'' + ", retailPrice=" + retailPrice + ", reference='" + reference + '\'' + ", stock="
				+ stock + ", tax=" + tax + ", discontinued=" + discontinued + ", provider=" + providerId + '}';
	}

	public static class Builder {
		private Article article;

		private Builder(String code) {
			this.article = new Article();
			this.article.code = code;
			this.article.discontinued = false;
			this.article.stock = 0;
			this.article.tax = Tax.GENERAL;
		}

		public Builder reference(String reference) {
			this.article.reference = reference;
			return this;
		}

		public Builder description(String description) {
			this.article.description = description;
			return this;
		}

		public Builder retailPrice(String retailPrice) {
			this.article.retailPrice = new BigDecimal(retailPrice);
			return this;
		}

		public Builder retailPrice(BigDecimal retailPrice) {
			this.article.retailPrice = retailPrice;
			return this;
		}

		public Builder stock(Integer stock) {
			this.article.stock = stock;
			return this;
		}

		public Builder providerId(String providerId) {
			this.article.providerId = providerId;
			return this;
		}

		public Builder tax(Tax tax) {
			this.article.tax = tax;
			return this;
		}

		public Builder discontinued(Boolean discontinued) {
			this.article.discontinued = discontinued;
			return this;
		}

		public Article build() {
			return this.article;
		}
	}

}