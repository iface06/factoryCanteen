

/* http://docs.angularjs.org/guide/dev_guide.e2e-testing */

describe('Find Menu Test', function() {

    beforeEach(function() {
        browser().navigateTo('/web/index.html');
    }); 

    it('menu for calendar week found', function() {
		expect(binding('menu.calendarWeek.week')).toBe(moment().week().toString());
		expect(binding('menu.calendarWeek.year')).toBe(moment().year().toString());
		expect(element('tbody.offers tr').count()).toBe(3);
		
    });
});
