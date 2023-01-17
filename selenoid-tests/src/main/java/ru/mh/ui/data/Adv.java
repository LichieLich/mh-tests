package ru.mh.ui.data;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(fluent = true)
public class Adv {
  private String town;
  private String title;
  private String description;
  private String paymentMethod;
}
