AlphaNumericComparator
======================
Sorter that compares the given Alpha-numeric strings. This iterates through
each characters to decide the sort order. There are 2 possible cases while
iterating,

<li>When the current character under comparison in at-least one of the given
String is a non-digit the particular character alone compared lexically.</li>

<li>When the current character under comparison in both of the String is a
digit, the consecutive digit characters in both the Strings will be
considered for comparison.</li>

<li>At any point if the comparison confirms the order(either > or <) then
the consecutive characters will not be considered.</li>

For ex., this will be the ordered O/P of the given list of Strings.(The bold
characters decides its order)
<i>
	<b>2</b>b,<b>100</b>b,a<b>1</b>,A<b>2</b>y,a<b>100</b>
</i>