import { OESWebPage } from './app.po';

describe('oes-web App', function() {
  let page: OESWebPage;

  beforeEach(() => {
    page = new OESWebPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
